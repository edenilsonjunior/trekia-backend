package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.tripmedias.CreateTripMediaRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TripMediaService implements ITripMediaService {
    @Override
    public ResponseEntity<?> createTripMedia(CreateTripMediaRequestDto createTripMediaRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getTripMediaBtTripId(long tripId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTripMedia(long tripId, long tripMediaId) {
        return null;
    }
}
