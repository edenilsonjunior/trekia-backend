package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.tripmedias.CreateTripMediaRequestDto;
import br.edu.ifsp.arq.trekia.dtos.tripmedias.TripMediaResponseDto;
import br.edu.ifsp.arq.trekia.models.entities.Trip;
import br.edu.ifsp.arq.trekia.models.entities.TripMedia;
import br.edu.ifsp.arq.trekia.models.repositories.TripMediaRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripMediaService;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripService;
import br.edu.ifsp.arq.trekia.util.MediaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripMediaService implements ITripMediaService {

    private final TripMediaRepository tripMediaRepository;
    private final ITripService tripService;

    @Autowired
    public TripMediaService(TripMediaRepository tripMediaRepository, ITripService tripService) {
        this.tripMediaRepository = tripMediaRepository;
        this.tripService = tripService;
    }

    @Override
    public ResponseEntity<?> createTripMedia(long tripId, CreateTripMediaRequestDto createTripMediaRequest) {

        var trip = tripService
                .findByTripId(tripId).orElse(null);

        if (trip == null) {
            return Result.toResponse("Viagem não encontrada", HttpStatus.NOT_FOUND);
        }

        TripMedia tripMedia = new TripMedia();
        tripMedia.setTrip(trip);
        tripMedia.setDescription(createTripMediaRequest.getDescription());
        tripMedia.setMedia(MediaUtil.convertMultipartFileToBytes(createTripMediaRequest.getMedia()));

        tripMediaRepository.save(tripMedia);

        return Result.toResponse(null, "Imagem adicionada com sucesso", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getTripMediaByTripId(long tripId) {
        List<TripMedia> tripMedia = tripMediaRepository.findByTripId(tripId);

        if (tripMedia.isEmpty()) {
            return Result.toResponse(List.of(), "Imagem não encontrada para esta viagem", HttpStatus.OK);
        }

        var tripMediaDto = tripMedia.stream()
                .map(media -> new TripMediaResponseDto(
                        media.getId(),
                        media.getMedia(),
                        media.getDescription()
                ))
                .toList();

        return Result.toResponse(tripMediaDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTripMedia(long tripId, long tripMediaId) {
        Optional<TripMedia> tripMediaOptional = tripMediaRepository.findById(tripMediaId);

        if (tripMediaOptional.isEmpty() || tripMediaOptional.get().getTrip().getId() != tripId) {
            return Result.toResponse("Imagem não encontrada para esta viagem", HttpStatus.NOT_FOUND);
        }

        tripMediaRepository.deleteById(tripMediaId);
        return Result.toResponse("Imagem removida com sucesso", HttpStatus.NO_CONTENT);

    }
}
