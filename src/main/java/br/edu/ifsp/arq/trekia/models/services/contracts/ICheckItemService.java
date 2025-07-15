package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.checkitems.*;
import org.springframework.http.ResponseEntity;

public interface ICheckItemService {

    ResponseEntity<?> getCheckItemsByTripId(long tripId);

    ResponseEntity<?> createCheckItem(long tripId, CreateCheckItemRequestDto createCheckItemRequest);

    ResponseEntity<?> toggleCheckItemChecked(long tripId, long checkItemId);

    ResponseEntity<?> deleteCheckItem(long tripId, long checkItemId);
}