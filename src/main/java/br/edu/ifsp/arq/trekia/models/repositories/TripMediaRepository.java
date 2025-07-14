package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.TripMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripMediaRepository extends JpaRepository<TripMedia, Long> {
}
