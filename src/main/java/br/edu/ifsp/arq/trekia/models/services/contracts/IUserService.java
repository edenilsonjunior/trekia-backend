package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.users.*;
import br.edu.ifsp.arq.trekia.models.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUserService {

    ResponseEntity<?> login(LoginRequestDto loginRequest);

    ResponseEntity<?> register(RegisterRequestDto registerRequest);

    ResponseEntity<?> getUserById(long id);

    ResponseEntity<?> updateUser(long id, UpdateUserRequestDto updateUserRequest);

    Optional<User> getAuthenticatedUser();
}
