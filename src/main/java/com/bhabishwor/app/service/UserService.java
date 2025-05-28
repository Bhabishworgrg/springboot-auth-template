package com.bhabishwor.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bhabishwor.app.entity.UserEntity;

@Service
public class UserService {
	private static final String EXISTING_EMAIL = "test@email.com";

	public Optional<UserEntity> findByEmail(String email) {
		if(!EXISTING_EMAIL.equalsIgnoreCase(email)) {
			return Optional.empty();
		}

		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setEmail(EXISTING_EMAIL);
		user.setPassword("$2a$12$ZrHClEY3PVH9kty.qkfftuLEdmPUMJ6jXadknxXBZvmT/AjQXjCcu");
		user.setRole("ADMIN");
		user.setExtraInfo("This is admin");
		return Optional.of(user);
	}
}
