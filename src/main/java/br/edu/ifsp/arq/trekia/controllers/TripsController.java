package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.dtos.schedules.create.CreateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.update.UpdateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    private final ICheckItemService checkItemService;
    private final IScheduleService scheduleService;
    private final ITripMediaService tripMediaService;
    private final ITripService tripService;
    private final IUserService userService;

    @Autowired
    public TripsController(ICheckItemService checkItemService, IScheduleService scheduleService, ITripMediaService tripMediaService, ITripService tripService, IUserService userService) {
        this.checkItemService = checkItemService;
        this.scheduleService = scheduleService;
        this.tripMediaService = tripMediaService;
        this.tripService = tripService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createTrip(@RequestBody CreateTripRequestDto createTripRequest) {
        return tripService.createTrip(createTripRequest);
    }

    @GetMapping
    public ResponseEntity<?> getTripsByUser() {

        var user = userService.getAuthenticatedUser();

        return tripService.getTripsByUserId(user.get().getId());
    }

    // SCHEDULES SECTION
    @GetMapping("/{tripId}/schedules")
    public ResponseEntity<?> getSchedulesByTripId(@PathVariable long tripId) {
        return scheduleService.getSchedulesByTripId(tripId);
    }

    @GetMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedulesById(@PathVariable long tripId, @PathVariable long scheduleId) {
        return scheduleService.getSchedulesById(tripId, scheduleId);
    }

    @PostMapping("{tripId}/schedules")
    public ResponseEntity<?> createSchedule(@PathVariable long tripId, @RequestBody CreateScheduleRequestDto createScheduleRequest) {
        return scheduleService.createSchedule(tripId, createScheduleRequest);
    }

    @PutMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable long tripId,
            @PathVariable long scheduleId,
            @RequestBody UpdateScheduleRequestDto updateScheduleRequest) {
        return scheduleService.updateSchedule(tripId, scheduleId, updateScheduleRequest);
    }

    @PatchMapping("/{tripId}/schedules/{scheduleId}/renew-weather")
    public ResponseEntity<?> renewWeather(@PathVariable long tripId, @PathVariable long scheduleId) {
        return scheduleService.renewWeather(tripId, scheduleId);
    }

    @PatchMapping("/{tripId}/schedules/{scheduleId}/renew-currency")
    public ResponseEntity<?> renewCurrency(@PathVariable long tripId, @PathVariable long scheduleId) {
        return scheduleService.renewCurrency(tripId, scheduleId);
    }

    @DeleteMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable long tripId, @PathVariable long scheduleId) {
        return scheduleService.deleteSchedule(tripId, scheduleId);
    }
}
