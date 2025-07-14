package br.edu.ifsp.arq.trekia.dtos.users;

public record UpdateUserRequestDto(
        String name,
        String password
) {
}