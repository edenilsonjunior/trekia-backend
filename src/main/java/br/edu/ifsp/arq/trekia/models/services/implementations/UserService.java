package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.users.LoginRequestDto;
import br.edu.ifsp.arq.trekia.dtos.users.RegisterRequestDto;
import br.edu.ifsp.arq.trekia.dtos.users.UpdateUserRequestDto;
import br.edu.ifsp.arq.trekia.models.entities.User;
import br.edu.ifsp.arq.trekia.models.repositories.UserRepository;
import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.ITokenService;
import br.edu.ifsp.arq.trekia.models.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ITokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ITokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDto loginRequest) {

        var user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {

            return Result.toResponse("Senha incorreta", HttpStatus.UNAUTHORIZED);
        }

        var token = tokenService.generateToken(user);

        return Result.toResponse(token, "Login realizado com sucesso", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequestDto registerRequest) {

        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            return Result.toResponse("Email já cadastrado", HttpStatus.BAD_REQUEST);
        }

        var user = new User();
        user.setName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        userRepository.save(user);

        return Result.toResponse("Usuário cadastrado com sucesso", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getUserById(long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            return Result.toResponse("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        return Result.toResponse(user.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUser(long id, UpdateUserRequestDto updateUserRequest) {
        var user = userRepository.findById(id).orElse(null);

        if( user == null) {
            return Result.toResponse("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        user.setName(updateUserRequest.name());

        if (updateUserRequest.password() != null && !updateUserRequest.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateUserRequest.password()));
        }

        userRepository.save(user);

        return Result.toResponse("Usuário atualizado com sucesso", HttpStatus.OK);
    }

    @Override
    public Optional<User> getAuthenticatedUser() {

        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email);
    }
}