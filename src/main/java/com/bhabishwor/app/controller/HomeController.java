package com.bhabishwor.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "Welcome!";
	}

	@GetMapping("/test/secured")
	public String secured() {
		return "You are logged in";
	}
}
