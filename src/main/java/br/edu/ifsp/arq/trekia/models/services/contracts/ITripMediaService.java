package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.tripmedias.*;
import org.springframework.http.ResponseEntity;

public interface ITripMediaService {

    ResponseEntity<?> createTripMedia(long tripId, CreateTripMediaRequestDto createTripMediaRequest);
    ResponseEntity<?> getTripMediaByTripId(long tripId);
    ResponseEntity<?> deleteTripMedia(long tripId, long tripMediaId);
}
