package com.bhabishwor.app.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {
	public UserPrincipal convert(DecodedJWT jwt) {
		return UserPrincipal.builder()
			.userId(Long.valueOf(jwt.getSubject()))
			.email(jwt.getClaim("email").asString())
			.authorities(getAuthoritiesFromClaim(jwt))
			.build();
	}
	
	private List<SimpleGrantedAuthority> getAuthoritiesFromClaim(DecodedJWT jwt) {
		Claim claim = jwt.getClaim("roles");
		if (claim.isNull() || claim.isMissing()) {
			return List.of();
		}
		return claim.asList(SimpleGrantedAuthority.class);
	}
}
