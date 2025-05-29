package com.bhabishwor.app.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
	private final JwtProperties properties;

	public String createToken(long userId, String email) {
		return JWT.create()
			.withSubject(String.valueOf(userId))
			.withClaim("email", email)
			.withExpiresAt(Instant.now().plus(30, ChronoUnit.DAYS))
			.sign(Algorithm.HMAC256(properties.getSecretKey()));
	}
}
