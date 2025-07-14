package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.schedules.*;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {


    ResponseEntity<?> getSchedulesByTripId(long tripId);

    ResponseEntity<?> getSchedulesById(long tripId, long scheduleId);

    ResponseEntity<?> createSchedule(CreateScheduleRequestDto createScheduleRequest);

    ResponseEntity<?> updateSchedule(long tripId, long scheduleId, UpdateScheduleRequestDto updateScheduleRequest);

    ResponseEntity<?> renewWeather(long tripId, long scheduleId);

    ResponseEntity<?> renewCurrency(long tripId, long scheduleId);

    ResponseEntity<?> deleteSchedule(long tripId, long scheduleId);
}