package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.dtos.checkitems.CreateCheckItemRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.create.CreateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.schedules.update.UpdateScheduleRequestDto;
import br.edu.ifsp.arq.trekia.dtos.tripmedias.CreateTripMediaRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.UpdateTripRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    private final ICheckItemService checkItemService;
    private final IScheduleService scheduleService;
    private final ITripMediaService tripMediaService;
    private final ITripService tripService;

    @Autowired
    public TripsController(ICheckItemService checkItemService, IScheduleService scheduleService, ITripMediaService tripMediaService, ITripService tripService) {
        this.checkItemService = checkItemService;
        this.scheduleService = scheduleService;
        this.tripMediaService = tripMediaService;
        this.tripService = tripService;
    }

    @PostMapping()
    public ResponseEntity<?> createTrip(@RequestBody CreateTripRequestDto createTripRequest) {
        return tripService.createTrip(createTripRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable long id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable long id, @RequestBody UpdateTripRequestDto updateTripRequest) {
        return tripService.updateTrip(id, updateTripRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable long id) {
        return tripService.deleteTrip(id);
    }

    @PostMapping("/{id}/check-items")
    public ResponseEntity<?> createCheckItem(@PathVariable long id, @RequestBody CreateCheckItemRequestDto createCheckItemRequest) {
        return checkItemService.createCheckItem(id,createCheckItemRequest);
    }

    @GetMapping("/{id}/check-items")
    public ResponseEntity<?> getCheckItemsByTripId(@PathVariable long id) {
        return checkItemService.getCheckItemsByTripId(id);
    }

    @PatchMapping("/{id}/check-items/{checkItemId}/toggle")
    public ResponseEntity<?> toggleCheckItemChecked(@PathVariable long id, @PathVariable long checkItemId) {
        return checkItemService.toggleCheckItemChecked(id, checkItemId);
    }

    @DeleteMapping("/{id}/check-items/{checkItemId}")
    public ResponseEntity<?> deleteCheckItem(@PathVariable long id, @PathVariable long checkItemId) {
        return checkItemService.deleteCheckItem(id, checkItemId);
    }

    @PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createTripMedia(@ModelAttribute CreateTripMediaRequestDto createTripMediaRequestDto) {
        return tripMediaService.createTripMedia(createTripMediaRequestDto);
    }

    @GetMapping("/{id}/media")
    public ResponseEntity<?> getTripMediaByTripId(@PathVariable long id) {
        return tripMediaService.getTripMediaByTripId(id);
    }

    @DeleteMapping("/{id}/media/{mediaId}")
    public ResponseEntity<?> deleteTripMedia(@PathVariable long id, @PathVariable long mediaId) {
        return tripMediaService.deleteTripMedia(id, mediaId);
    }

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
