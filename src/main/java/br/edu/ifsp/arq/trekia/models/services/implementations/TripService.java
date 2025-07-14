package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.UpdateTripRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripService;
import org.springframework.http.ResponseEntity;

public class TripService implements ITripService {
    @Override
    public ResponseEntity<?> createTrip(CreateTripRequestDto createTripRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getTripsByUserId(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getTripById(long tripId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateTrip(long tripId, UpdateTripRequestDto updateTripRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTrip(long tripId) {
        return null;
    }
}