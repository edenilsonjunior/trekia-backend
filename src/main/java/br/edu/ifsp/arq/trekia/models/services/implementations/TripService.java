package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.TripResponseDto;
import br.edu.ifsp.arq.trekia.dtos.trips.UpdateTripRequestDto;
import br.edu.ifsp.arq.trekia.models.entities.Trip;
import br.edu.ifsp.arq.trekia.models.repositories.TripRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripService;
import br.edu.ifsp.arq.trekia.models.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService implements ITripService {

    private final TripRepository tripRepository;
    private final IUserService userService;

    @Autowired
    public TripService(TripRepository tripRepository, IUserService userService) {
        this.tripRepository = tripRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createTrip(CreateTripRequestDto createTripRequest) {
        var userOpt = userService.getAuthenticatedUser();

        if (userOpt.isEmpty()) {
            return Result.toResponse("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
        }

        var trip = new Trip();
        trip.setUser(userOpt.get());
        trip.setTitle(createTripRequest.title());
        trip.setDescription(createTripRequest.description());
        trip.setStartDate(createTripRequest.startDate());
        trip.setEndDate(createTripRequest.endDate());

        tripRepository.save(trip);

        return Result.toResponse("Viagem cadastrada com sucesso", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getTripsByUserId(long userId) {
        var trips = tripRepository.findByUserId(userId);

        if (trips.isEmpty()) {
            return Result.toResponse("Nenhuma viagem encontrada.", HttpStatus.NO_CONTENT);
        }

        var tripListDto = trips.stream()
                .map(trip -> new TripResponseDto(
                        trip.getId(),
                        trip.getTitle(),
                        trip.getDescription(),
                        trip.getStartDate(),
                        trip.getEndDate()
                ))
                .toList();

        return Result.toResponse(tripListDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTripById(long tripId) {
        var tripOpt = tripRepository.findById(tripId);

        if (tripOpt.isEmpty()) {
            return Result.toResponse("Viagem não encontrada.", HttpStatus.NOT_FOUND);
        }

        var trip = tripOpt.get();
        var tripResponse = new TripResponseDto(
                trip.getId(),
                trip.getTitle(),
                trip.getDescription(),
                trip.getStartDate(),
                trip.getEndDate()
        );

        return Result.toResponse(tripResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTrip(long tripId, UpdateTripRequestDto updateTripRequest) {
        var trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        trip.setTitle(updateTripRequest.title());
        trip.setDescription(updateTripRequest.description());
        trip.setStartDate(updateTripRequest.startDate());
        trip.setEndDate(updateTripRequest.endDate());

        tripRepository.save(trip);

        return Result.toResponse("Viagem atualizada com sucesso", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTrip(long tripId) {
        var trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        tripRepository.delete(trip);

        return Result.toResponse("Viagem deletada com sucesso", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> deleteTrips(List<Long> ids) {

        for (Long id : ids) {
            this.deleteTrip(id);
        }

        return Result.toResponse("Viagens deletadas com sucesso", HttpStatus.NO_CONTENT);
    }

    @Override
    public Optional<Trip> findByTripId(long tripId) {
        return tripRepository.findById(tripId);
    }
}