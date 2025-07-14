package br.edu.ifsp.arq.trekia.dtos.users;

public record RegisterRequestDto(
        String name,
        String email,
        String password
) { }