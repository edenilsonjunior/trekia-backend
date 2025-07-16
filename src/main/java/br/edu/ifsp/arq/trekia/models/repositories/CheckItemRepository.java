package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckItemRepository extends JpaRepository<CheckItem, Long> {
    List<CheckItem> findByTripId(Long tripId);
}
