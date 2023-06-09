package com.victortavin.marmitaria.filters;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.victortavin.marmitaria.controllers.exceptions.StandardError;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@EnableAutoConfiguration
public class TokenFilter extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = recoverToken(request);
		
		UserEntity user = null;
		   if (token == null) {
			   if(!request.getRequestURI().equals("/users/login")
					   && !request.getRequestURI().equals("/users/cadastro")
					   && !request.getRequestURI().equals("/h2-console")) {
				   	ObjectMapper mapper = new ObjectMapper();
			        StandardError erro = new StandardError(Instant.now(), 403, "Acces Denied", "Usuário não está autenticado", request.getRequestURI());
			        String json = mapper.writeValueAsString(erro);
			        
			        response.setContentType("application/json");
			        response.setCharacterEncoding("UTF-8");
			        response.setStatus(403);
			        response.getWriter().write(json);
			        
			        return;
			        
			   } 
		    }
		
		if(token != null) {
			String subjetc = tokenService.getSubject(token);

			user = userRepository.findByEmail(subjetc);
			
			if(user != null) {
				var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			UserEntity userEntity = userRepository.findByEmail(subjetc);
			
			var authentication = new UsernamePasswordAuthenticationToken(userEntity, userEntity.getId(), userEntity.getAuthorities());
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	

	public String recoverToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}

}
