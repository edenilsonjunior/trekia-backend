package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.dtos.trips.CreateTripRequestDto;
import br.edu.ifsp.arq.trekia.dtos.users.RegisterRequestDto;
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

}
