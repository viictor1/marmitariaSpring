package com.victortavin.marmitaria.security.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String email;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ") ) {
			filterChain.doFilter(request, response);
		}
		
		jwt = authHeader.substring(7);
		
	}

}
