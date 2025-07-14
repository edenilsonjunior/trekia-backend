package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}