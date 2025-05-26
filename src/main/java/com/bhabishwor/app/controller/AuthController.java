package com.bhabishwor.app.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhabishwor.app.model.LoginRequest;
import com.bhabishwor.app.model.LoginResponse;
import com.bhabishwor.app.security.JwtIssuer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final JwtIssuer jwtIssuer;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		String token = jwtIssuer.createToken(
			1L,
			request.getEmail(),
			List.of("USER")
		);

		return LoginResponse.builder()
			.accessToken(token)
			.build();
	}
}
