package com.bhabishwor.app.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bhabishwor.app.model.AppUser;
import com.bhabishwor.app.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private static final String EXISTING_EMAIL = "test@email.com";

	private Optional<AppUser> findByEmail(String email) {
		if(!EXISTING_EMAIL.equalsIgnoreCase(email)) {
			return Optional.empty();
		}

		AppUser user = new AppUser();
		user.setId(1L);
		user.setEmail(EXISTING_EMAIL);
		user.setPassword("$2a$12$ZrHClEY3PVH9kty.qkfftuLEdmPUMJ6jXadknxXBZvmT/AjQXjCcu");
		return Optional.of(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = findByEmail(email).orElseThrow();
		return UserPrincipal.builder()
			.userId(user.getId())
			.email(user.getEmail())
			.password(user.getPassword())
			.build();
	}
}
