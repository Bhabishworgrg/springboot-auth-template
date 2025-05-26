package com.bhabishwor.app.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("jwt")
@Getter
@Setter
public class JwtProperties {
	private String secretKey;
}
