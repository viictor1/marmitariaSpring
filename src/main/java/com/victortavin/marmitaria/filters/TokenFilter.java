package com.victortavin.marmitaria.filters;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired  // Precisa disso para criar o mapper que transforma o erro em json 
    Jackson2ObjectMapperBuilder mapperBuilder;
	
	String[] publicPaths = {"/api/auth",
	          "/v3/api-docs.yaml",
	          "/v3/api-docs",
	          "/swagger-ui",
	          "/swagger-ui.html",
	          "/users/login",
	          "/users/cadastro",
	          "/h2-console",
	          "swagger-ui"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = recoverToken(request);
		
		
		UserEntity user = null;
		   if (token == null) {
			   boolean isPublic = false;
			   for (String path : publicPaths) {
				   if(request.getRequestURI().startsWith(path)) {
					   isPublic = true;
				   }
			   }
			
			   if(isPublic == false) {
				   
				   	ObjectMapper mapper = mapperBuilder.build();				   
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
