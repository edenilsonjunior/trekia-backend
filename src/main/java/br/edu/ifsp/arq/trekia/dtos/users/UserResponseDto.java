package br.edu.ifsp.arq.trekia.dtos.users;

import java.util.Optional;

public record UserResponseDto(
        String name,
        String email,
        Optional<String> token
) {
}
