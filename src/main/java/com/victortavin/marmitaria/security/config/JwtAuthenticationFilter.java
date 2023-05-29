package com.victortavin.marmitaria.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	public JwtService jwtService;
	
	@Autowired
	public UserDetailsService userDetailsService;
	
	protected void doFilterInternal(@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String authHeader = request.getHeader("Authorization");
		String jwt;
		String email;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ") ) {
			filterChain.doFilter(request, response);
		}
		else {
			
			jwt = authHeader.substring(7);
			email = jwtService.extractUsername(jwt);
			
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				System.out.println("segundo if");
				// O if verifica se o usuário é válido e se já nao está autenticado 
				
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
				
				if(jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new 
							UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				
				filterChain.doFilter(request, response);
			}
		}
		
		
	}

}
