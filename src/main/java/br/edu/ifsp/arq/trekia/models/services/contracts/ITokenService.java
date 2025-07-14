package br.edu.ifsp.arq.trekia.models.services.contracts;


import br.edu.ifsp.arq.trekia.models.entities.User;

public interface ITokenService {

    String generateToken(User user);

    String extractEmail(String token);

    boolean isTokenValid(String token);
}