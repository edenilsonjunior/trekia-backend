package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.dashboard.DashboardScheduleDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.DashboardTripDto;
import br.edu.ifsp.arq.trekia.models.entities.TripMedia;
import br.edu.ifsp.arq.trekia.models.repositories.ScheduleRepository;
import br.edu.ifsp.arq.trekia.models.repositories.TripMediaRepository;
import br.edu.ifsp.arq.trekia.models.repositories.TripRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService implements IDashboardService {

    private ScheduleRepository scheduleRepository;
    private IExternalApiService externalApiService;
    private IUserService userService;
    private ITripService tripService;
    private TripMediaRepository tripMediaRepository;
    private TripRepository tripRepository;

    @Autowired
    public DashboardService(ScheduleRepository scheduleRepository, IExternalApiService externalApiService, IUserService userService, ITripService tripService, TripMediaRepository tripMediaRepository, TripRepository tripRepository) {
        this.scheduleRepository = scheduleRepository;
        this.externalApiService = externalApiService;
        this.userService = userService;
        this.tripService = tripService;
        this.tripMediaRepository = tripMediaRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public ResponseEntity<?> getTripsByUserId(long userId) {

        var trips = tripRepository.findByUserId(userId);

        var response = trips.stream().map(trip -> {

            var mediaBase64 = tripMediaRepository.findFirstByTripId(trip.getId())
                    .map(TripMedia::getMediaBase64)
                    .orElse("");

            var schedules = scheduleRepository.getSchedulesByTripId(trip.getId())
                    .stream()
                    .map(schedule -> new DashboardScheduleDto(
                            schedule.getId(),
                            schedule.getTitle(),
                            schedule.getLatitude(),
                            schedule.getLongitude()
                    )).toList();

            return new DashboardTripDto(
                    trip.getId(),
                    trip.getTitle(),
                    trip.getDescription(),
                    mediaBase64,
                    trip.getStartDate().toString(),
                    trip.getEndDate().toString(),
                    trip.getCreatedAt().toString(),
                    schedules
            );
        }).toList();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getWeather(BigDecimal latitude, BigDecimal longitude) {

        var weatherResponse = externalApiService.getWeather(latitude, longitude);

        if (weatherResponse.isEmpty()) {
            return Result.toResponse("Erro ao obter dados climáticos", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Result.toResponse(weatherResponse.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCurrency(String fromCurrency, String toCurrency) {

        var currencyResponse = externalApiService.getCurrentLocalBalance(fromCurrency, List.of(toCurrency));

        if (currencyResponse.isEmpty()) {
            return Result.toResponse("Erro ao obter dados de câmbio", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(currencyResponse.get());
    }

    @Override
    public ResponseEntity<?> getCurrencyCodes() {
        var currencyCodesResponse = externalApiService.getCurrencyCodes();

        if (currencyCodesResponse.isEmpty()) {
            return Result.toResponse("Erro ao obter códigos de moedas", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Result.toResponse(currencyCodesResponse.get().currencies(), HttpStatus.OK);
    }
}
