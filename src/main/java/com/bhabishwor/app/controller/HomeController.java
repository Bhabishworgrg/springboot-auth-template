package com.bhabishwor.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhabishwor.app.security.UserPrincipal;

@RestController
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "Welcome!";
	}

	@GetMapping("/test/secured")
	public String secured(@AuthenticationPrincipal UserPrincipal principal) {
		return "You are logged in." +
			"\nEmail: " + principal.getEmail() +
			"\nUser ID: " + principal.getUserId();
	}
}
