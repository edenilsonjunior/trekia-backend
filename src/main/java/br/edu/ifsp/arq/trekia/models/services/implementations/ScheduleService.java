package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.schedules.create.CreateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.create.CreateScheduleResponseDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.get.GetScheduleDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.update.UpdateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.update.UpdateScheduleResponseDto;
import br.edu.ifsp.arq.trekia.models.entities.Schedule;
import br.edu.ifsp.arq.trekia.models.repositories.ScheduleRepository;
import br.edu.ifsp.arq.trekia.models.repositories.TripRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.IExternalApiService;
import br.edu.ifsp.arq.trekia.models.services.contracts.IScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TripRepository tripRepository;
    private final IExternalApiService externalApiService;

    public ScheduleService(ScheduleRepository scheduleRepository, TripRepository tripRepository,
                           IExternalApiService externalApiService) {
        this.scheduleRepository = scheduleRepository;
        this.tripRepository = tripRepository;
        this.externalApiService = externalApiService;
    }

    @Override
    public ResponseEntity<?> getSchedulesByTripId(long tripId) {

        var schedules = scheduleRepository.getSchedulesByTripId(tripId);

        var response = schedules.stream()
                .map(schedule -> new GetScheduleDto(
                        schedule.getId(),
                        schedule.getTrip().getTitle(),
                        schedule.getPlannedAt(),
                        schedule.getTitle(),
                        schedule.getDescription(),
                        schedule.getLatitude(),
                        schedule.getLongitude(),
                        schedule.getCurrentLocalBalance(),
                        schedule.getCurrencyCode(),
                        schedule.getMinTemperature(),
                        schedule.getMaxTemperature(),
                        schedule.getPrecipitationChance()
                )).toList();

        return Result.toResponse(schedules, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getSchedulesById(long tripId, long scheduleId) {

        var schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule == null || schedule.getTrip().getId() != tripId) {
            return Result.toResponse("Roteiro não encontrado para a viagem", HttpStatus.NOT_FOUND);
        }

        var response = new GetScheduleDto(
                schedule.getId(),
                schedule.getTrip().getTitle(),
                schedule.getPlannedAt(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getLatitude(),
                schedule.getLongitude(),
                schedule.getCurrentLocalBalance(),
                schedule.getCurrencyCode(),
                schedule.getMinTemperature(),
                schedule.getMaxTemperature(),
                schedule.getPrecipitationChance()
        );

        return Result.toResponse(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createSchedule(long tripId, CreateScheduleRequestDto createScheduleRequest) {

        var trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        var schedule = new Schedule();
        schedule.setTrip(trip);
        schedule.setTitle(createScheduleRequest.title());
        schedule.setDescription(createScheduleRequest.description());
        schedule.setLatitude(createScheduleRequest.latitude());
        schedule.setLongitude(createScheduleRequest.longitude());
        schedule.setCurrencyCode(createScheduleRequest.currencyCode());

        var weather = externalApiService.getWeather(
                createScheduleRequest.latitude(),
                createScheduleRequest.longitude());
        var currency = externalApiService.getCurrentLocalBalance(schedule.getCurrencyCode(), List.of("BRL"));

        if (weather.isPresent()) {

            var weatherData = weather.get();

            schedule.setMaxTemperature(weatherData.maxTempC());
            schedule.setMinTemperature(weatherData.minTempC());
            schedule.setPrecipitationChance(weatherData.maxChanceOfRain());
        }

        currency.ifPresent(currencyData -> schedule.setCurrentLocalBalance(currencyData.getBrazilianReal()));

        scheduleRepository.save(schedule);

        var response = new CreateScheduleResponseDto(
                schedule.getId(),
                trip.getTitle(),
                schedule.getPlannedAt(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getLatitude(),
                schedule.getLongitude(),
                schedule.getCurrentLocalBalance(),
                schedule.getCurrencyCode(),
                schedule.getMinTemperature(),
                schedule.getMaxTemperature(),
                schedule.getPrecipitationChance()
        );

        return Result.toResponse(response, "Roteiro criado com sucesso", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateSchedule(long tripId, long scheduleId,
                                            UpdateScheduleRequestDto updateScheduleRequest) {

        if (!tripRepository.existsById(tripId)) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        var schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule == null || schedule.getTrip().getId() != tripId) {
            return Result.toResponse("Roteiro não encontrado para a viagem", HttpStatus.NOT_FOUND);
        }

        schedule.setTitle(updateScheduleRequest.title());
        schedule.setDescription(updateScheduleRequest.description());
        schedule.setLatitude(updateScheduleRequest.latitude());
        schedule.setLongitude(updateScheduleRequest.longitude());
        schedule.setCurrencyCode(updateScheduleRequest.currencyCode());

        var weather = externalApiService.getWeather(updateScheduleRequest.latitude(),
                updateScheduleRequest.longitude());
        var currency = externalApiService.getCurrentLocalBalance(schedule.getCurrencyCode(), List.of("BRL"));

        if (weather.isPresent()) {

            var weatherData = weather.get();

            schedule.setMaxTemperature(weatherData.maxTempC());
            schedule.setMinTemperature(weatherData.minTempC());
            schedule.setPrecipitationChance(weatherData.maxChanceOfRain());
        }

        currency.ifPresent(currencyData -> schedule.setCurrentLocalBalance(currencyData.getBrazilianReal()));

        scheduleRepository.save(schedule);

        var response = new UpdateScheduleResponseDto(
                schedule.getId(),
                schedule.getTrip().getTitle(),
                schedule.getPlannedAt(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getLatitude(),
                schedule.getLongitude(),
                schedule.getCurrentLocalBalance(),
                schedule.getCurrencyCode(),
                schedule.getMinTemperature(),
                schedule.getMaxTemperature(),
                schedule.getPrecipitationChance()
        );

        return Result.toResponse(response, "Roteiro atualizado com sucesso", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> renewWeather(long tripId, long scheduleId) {

        var schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule == null || schedule.getTrip().getId() != tripId) {
            return Result.toResponse("Roteiro não encontrado para a viagem", HttpStatus.NOT_FOUND);
        }

        var weather = externalApiService.getWeather(schedule.getLatitude(), schedule.getLongitude());

        if (weather.isEmpty()) {
            return Result.toResponse("Não foi possível obter as informações climáticas",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var weatherData = weather.get();
        schedule.setMaxTemperature(weatherData.maxTempC());
        schedule.setMinTemperature(weatherData.minTempC());
        schedule.setPrecipitationChance(weatherData.maxChanceOfRain());

        scheduleRepository.save(schedule);

        return Result.toResponse("Informações climáticas atualizadas com sucesso", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> renewCurrency(long tripId, long scheduleId) {
        var schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule == null || schedule.getTrip().getId() != tripId) {
            return Result.toResponse("Roteiro não encontrado para a viagem", HttpStatus.NOT_FOUND);
        }

        var currency = externalApiService.getCurrentLocalBalance(schedule.getCurrencyCode(), List.of("BRL"));

        if (currency.isEmpty()) {
            return Result.toResponse("Não foi possível obter as informações de câmbio",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var currencyData = currency.get();
        schedule.setCurrentLocalBalance(currencyData.getBrazilianReal());

        scheduleRepository.save(schedule);

        return Result.toResponse("Informações de câmbio atualizadas com sucesso", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSchedule(long tripId, long scheduleId) {

        var schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule == null || schedule.getTrip().getId() != tripId) {
            return Result.toResponse("Roteiro não encontrado para a viagem", HttpStatus.NOT_FOUND);
        }

        scheduleRepository.delete(schedule);

        return Result.toResponse("Roteiro deletado com sucesso", HttpStatus.OK);
    }
}
