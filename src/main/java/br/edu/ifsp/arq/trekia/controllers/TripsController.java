package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.dtos.checkitems.CreateCheckItemRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.dtos.trips.UpdateTripRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    ICheckItemService checkItemService;
    IScheduleService scheduleService;
    ITripMediaService tripMediaService;
    ITripService tripService;
    IUserService userService;

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

    @GetMapping("/{id}/check-items")
    public ResponseEntity<?> getCheckItemsByTripId(@PathVariable long id) {
        return checkItemService.getCheckItemsByTripId(id);
    }

    @PostMapping("/{id}/check-items")
    public ResponseEntity<?> createCheckItem(@PathVariable long id, @RequestBody CreateCheckItemRequestDto createCheckItemRequest) {
        return checkItemService.createCheckItem(id,createCheckItemRequest);
    }

    @PatchMapping("/{id}/check-items/{checkItemId}/toggle")
    public ResponseEntity<?> toggleCheckItemChecked(@PathVariable long id, @PathVariable long checkItemId) {
        return checkItemService.toggleCheckItemChecked(id, checkItemId);
    }

    @DeleteMapping("/{id}/check-items/{checkItemId}")
    public ResponseEntity<?> deleteCheckItem(@PathVariable long id, @PathVariable long checkItemId) {
        return checkItemService.deleteCheckItem(id, checkItemId);
    }

}
