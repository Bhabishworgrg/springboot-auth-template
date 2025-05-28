package com.bhabishwor.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtAuthenticatedFilter jwtAuthenticatedFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterBefore(jwtAuthenticatedFilter, UsernamePasswordAuthenticationFilter.class);
		http
			.formLogin(form -> form.disable())
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			.securityMatcher("/**")
			.authorizeHttpRequests(registry -> registry
				.requestMatchers("/", "/login").permitAll()
				.anyRequest().authenticated()
			);

		return http.build();
	}
}
