package br.edu.ifsp.arq.trekia.dtos.users;

public record LoginRequestDto(
        String email,
        String password
) { }