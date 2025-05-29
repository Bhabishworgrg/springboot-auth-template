package com.bhabishwor.app.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhabishwor.app.model.AppUser;
import com.bhabishwor.app.dto.LoginRequest;
import com.bhabishwor.app.dto.LoginResponse;
import com.bhabishwor.app.dto.RegisterRequest;
import com.bhabishwor.app.repository.UserRepository;
import com.bhabishwor.app.security.JwtIssuer;
import com.bhabishwor.app.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final JwtIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping(value = "/login", consumes = "application/json")
	public LoginResponse login(@RequestBody LoginRequest request) {
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

	@PostMapping(value = "/register", consumes = "application/json")
	public AppUser register(@RequestBody RegisterRequest request) {
		AppUser user = AppUser.builder()
			.username(request.getUsername())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		return userRepository.save(user);
	}
}
