package com.victortavin.marmitaria.service.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void validateAdmin(HttpServletRequest request) {
		UserEntity user = validateLogado(request);
		
		if(!user.getRole().getName().equals("Admin")) {
			throw new ForbiddenException("Usuário não é um administrador");
		}	
	}
	
	public UserEntity validateLogado(HttpServletRequest request) {
		var token = filter.recoverToken(request);
		UserEntity user = null;
		
		if(token != null) {
			String subjetc = tokenService.getSubject(token);
			user = userRepository.findByEmail(subjetc);
		}
		
		if(user == null) {
			throw new ForbiddenException("Usuário não está logado");
		}
		
		return user;
			
	}
}
