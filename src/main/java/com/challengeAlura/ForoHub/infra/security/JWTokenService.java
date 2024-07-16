package com.challengeAlura.ForoHub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challengeAlura.ForoHub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTokenService {

    @Value("${forohub.security.secret}")
    private String secret;

    public String generarJWT(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("forohub alura")
                    .withSubject(usuario.getNombre())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

    public String verificarJwt(String bearerToken) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("forohub alura")
                    .build();
            decodedJWT = verifier.verify(bearerToken);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException(exception.getMessage());
        }
    }
}
