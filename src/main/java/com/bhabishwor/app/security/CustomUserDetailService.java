package com.bhabishwor.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bhabishwor.app.entity.UserEntity;
import com.bhabishwor.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userService.findByEmail(username).orElseThrow();
		return UserPrincipal.builder()
			.userId(user.getId())
			.email(user.getEmail())
			.password(user.getPassword())
			.build();
	}
}
