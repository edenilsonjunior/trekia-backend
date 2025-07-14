package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.checkitems.CreateCheckItemRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.ICheckItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckItemService implements ICheckItemService {
    @Override
    public ResponseEntity<?> getCheckItemsByTripId(long tripId) {
        return null;
    }

    @Override
    public ResponseEntity<?> createCheckItem(CreateCheckItemRequestDto createCheckItemRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> toggleCheckItemChecked(long tripId, long checkItemId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCheckItem(long tripId, long checkItemId) {
        return null;
    }
}
