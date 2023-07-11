package com.victortavin.marmitaria.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.victortavin.marmitaria.entities.UserEntity;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(UserEntity user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("Api marmitaria")
		        .withSubject(user.getEmail())
		        .withClaim("Role", user.getRole().getName())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar token");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(730).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getSubject(String token) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
		        .withIssuer("Api marmitaria")
		        .build()
		        .verify(token)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
		    // Invalid signature/claims
			throw new RuntimeException(exception);
		}
	}
}
