package com.bhabishwor.app.controller;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhabishwor.app.model.LoginRequest;
import com.bhabishwor.app.model.LoginResponse;
import com.bhabishwor.app.security.JwtIssuer;
import com.bhabishwor.app.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final JwtIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

		String token = jwtIssuer.createToken(
			principal.getUserId(),
			principal.getEmail()
		);

		return LoginResponse.builder()
			.accessToken(token)
			.build();
	}
}
