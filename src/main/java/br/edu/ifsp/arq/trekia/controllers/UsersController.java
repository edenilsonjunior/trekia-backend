package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.dtos.users.LoginRequestDto;
import br.edu.ifsp.arq.trekia.dtos.users.RegisterRequestDto;
import br.edu.ifsp.arq.trekia.dtos.users.UpdateUserRequestDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITripService;
import br.edu.ifsp.arq.trekia.models.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    IUserService userService;
    ITripService tripService;

    @Autowired
    public UsersController(IUserService userService, ITripService tripService) {
        this.userService = userService;
        this.tripService = tripService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequest) {
        return userService.register(registerRequest);
    }

    @GetMapping
    public ResponseEntity<?> getUserById() {

        var user = userService.getAuthenticatedUser();

        return userService.getUserById(user.get().getId());
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(
            @RequestBody UpdateUserRequestDto updateUserRequest
    ) {
        var user = userService.getAuthenticatedUser();

        return userService.updateUser(user.get().getId(), updateUserRequest);
    }

    @GetMapping("/{id}/trips")
    public ResponseEntity<?> getTripsByUserId(@PathVariable long id) {
        return tripService.getTripsByUserId(id);
    }
}