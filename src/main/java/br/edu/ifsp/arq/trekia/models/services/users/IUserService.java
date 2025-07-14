package br.edu.ifsp.arq.trekia.models.services.users;

import br.edu.ifsp.arq.trekia.dtos.users.*;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<?> login(LoginRequestDto loginRequest);
    ResponseEntity<?> register(RegisterRequestDto registerRequest);
    ResponseEntity<?> getUserById(long id);
    ResponseEntity<?> updateUser(long id, UpdateUserRequestDto updateUserRequest);
}
