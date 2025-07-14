package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.schedules.CreateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.UpdateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.IScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {
    @Override
    public ResponseEntity<?> getSchedulesByTripId(long tripId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getSchedulesById(long tripId, long scheduleId) {
        return null;
    }

    @Override
    public ResponseEntity<?> createSchedule(CreateScheduleRequestDto createScheduleRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateSchedule(long tripId, long scheduleId, UpdateScheduleRequestDto updateScheduleRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> renewWeather(long tripId, long scheduleId) {
        return null;
    }

    @Override
    public ResponseEntity<?> renewCurrency(long tripId, long scheduleId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSchedule(long tripId, long scheduleId) {
        return null;
    }
}
