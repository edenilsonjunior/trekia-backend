package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckItemRepository extends JpaRepository<CheckItem, Long> {
}
