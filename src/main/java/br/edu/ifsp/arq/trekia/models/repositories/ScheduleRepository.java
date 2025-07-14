package br.edu.ifsp.arq.trekia.models.repositories;

import br.edu.ifsp.arq.trekia.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
