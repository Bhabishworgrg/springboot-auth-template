package com.bhabishwor.app.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhabishwor.app.model.LoginRequest;
import com.bhabishwor.app.model.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		return LoginResponse.builder()
			.accessToken("test")
			.build();
	}
}
