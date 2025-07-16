package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.TripMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripMediaRepository extends JpaRepository<TripMedia, Long> {
    List<TripMedia> findByTripId(Long tripId);
}
