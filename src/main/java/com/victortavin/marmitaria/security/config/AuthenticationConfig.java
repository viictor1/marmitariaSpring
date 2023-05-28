package com.victortavin.marmitaria.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.victortavin.marmitaria.repositories.UserRepository;

@Configuration
public class AuthenticationConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username);
	}
}
