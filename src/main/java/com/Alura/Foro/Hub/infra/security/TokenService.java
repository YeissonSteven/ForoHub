package com.Alura.Foro.Hub.infra.security;

import com.Alura.Foro.Hub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API foro-hub")
                    .withSubject(usuario.getCorreo())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error generando token");
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(200).toInstant(ZoneOffset.of("-05:00"));
    }
    public String getSubjet(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API foro-hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (
                JWTVerificationException exception){
            throw new RuntimeException("Error token JWT invalido o expirado");
        }
    }
}
