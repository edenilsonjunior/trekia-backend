package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.checkitems.CheckItemResponseDto;
import br.edu.ifsp.arq.trekia.dtos.checkitems.CreateCheckItemRequestDto;
import br.edu.ifsp.arq.trekia.models.entities.CheckItem;
import br.edu.ifsp.arq.trekia.models.entities.Trip;
import br.edu.ifsp.arq.trekia.models.repositories.CheckItemRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.ICheckItemService;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckItemService implements ICheckItemService {

    private final CheckItemRepository checkItemRepository;
    private final ITripService tripService;

    @Autowired
    public CheckItemService(CheckItemRepository checkItemRepository, ITripService tripService) {
        this.checkItemRepository = checkItemRepository;
        this.tripService = tripService;
    }

    @Override
    public ResponseEntity<?> getCheckItemsByTripId(long tripId) {
        var checks = checkItemRepository.findByTripId(tripId);

        if (checks.isEmpty()) {
            return Result.toResponse("Nenhum check encontrado", HttpStatus.NO_CONTENT);
        }

        var checkListDto = checks.stream()
            .map(item -> new CheckItemResponseDto(
                item.getId(),
                item.getDescription(),
                item.getIsChecked()
            ))
            .toList();

        return Result.toResponse(checkListDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createCheckItem(long tripId,CreateCheckItemRequestDto createCheckItemRequest) {
        var checkItem = new CheckItem();
        Optional<Trip> tripOptional = tripService.findByTripId(tripId);

        if (tripOptional.isEmpty()) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        checkItem.setTrip(tripOptional.get());
        checkItem.setDescription(createCheckItemRequest.description());
        checkItem.setIsChecked(false);

        checkItemRepository.save(checkItem);

        return Result.toResponse("Check cadastrado com sucesso", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> toggleCheckItemChecked(long tripId, long checkItemId) {
        Optional<CheckItem> checkItemOptional = checkItemRepository.findById(checkItemId);

        if (checkItemOptional.isEmpty() || checkItemOptional.get().getTrip().getId() != tripId) {
            return Result.toResponse("Check não encontrado para esta viagem", HttpStatus.NOT_FOUND);
        }

        CheckItem checkItem = checkItemOptional.get();
        checkItem.setIsChecked(!checkItem.getIsChecked());

        checkItemRepository.save(checkItem);

        return Result.toResponse(
                String.format("Check alterado para %s com sucesso", checkItem.getIsChecked()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<?> deleteCheckItem(long tripId, long checkItemId) {
       Optional<CheckItem> checkItemOptional = checkItemRepository.findById(checkItemId);

        if (checkItemOptional.isEmpty() || checkItemOptional.get().getTrip().getId() != tripId) {
            return Result.toResponse("Check não encontrado para esta viagem", HttpStatus.NOT_FOUND);
        }

        checkItemRepository.deleteById(checkItemId);

        return Result.toResponse("Check removido com sucesso", HttpStatus.NO_CONTENT);
    }
}
