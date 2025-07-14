package br.edu.ifsp.arq.trekia.models.services.users;

import br.edu.ifsp.arq.trekia.models.entities.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService implements ITokenService {

    private final String SECRET = "supersecretkeysupersecretkeysupersecretkey";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    @Override
    public String generateToken(User user) {

        long EXPIRATION = 1000 * 60 * 60;

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}

