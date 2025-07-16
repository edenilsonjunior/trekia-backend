package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.trips.*;
import br.edu.ifsp.arq.trekia.models.entities.Trip;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ITripService {

    ResponseEntity<?> createTrip(CreateTripRequestDto createTripRequest);

    ResponseEntity<?> getTripsByUserId(long userId);

    ResponseEntity<?> getTripById(long tripId);

    ResponseEntity<?> updateTrip(long tripId, UpdateTripRequestDto updateTripRequest);

    ResponseEntity<?> deleteTrip(long tripId);

    Optional<Trip> findByTripId(long tripId);

    ResponseEntity<?> deleteTrips(List<Long> ids);
}