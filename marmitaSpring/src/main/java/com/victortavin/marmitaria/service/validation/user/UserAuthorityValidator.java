package com.victortavin.marmitaria.service.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.filters.TokenFilter;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.TokenService;
import com.victortavin.marmitaria.service.exceptions.ForbiddenException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserAuthorityValidator {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	TokenFilter filter;

	public void validateAdmin() {
		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			 if(!authority.getAuthority().equals("Admin")) {
				 throw new ForbiddenException("Usuário não é um administrador");
			 }
		}
			
	}
	
	public UserEntity validateLogado(HttpServletRequest request) {
		try {
			var token = filter.recoverToken(request);
			String subjetc = tokenService.getSubject(token);
			UserEntity user = userRepository.findByEmail(subjetc);
			return user;
		
		} catch (Exception e) {
			throw new ForbiddenException("Usuário não está logado");			
		}	
	}
}
