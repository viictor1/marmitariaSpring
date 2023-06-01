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
public class UserAdminValidator {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	TokenFilter filter;

	public boolean validateAdmin(HttpServletRequest request) {
		var token = filter.recoverToken(request);
		UserEntity user = null;
		
		if(token != null) {
			String subjetc = tokenService.getSubject(token);
			user = userRepository.findByEmail(subjetc);
		}
		
		if(user == null || !user.getRole().getName().equals("Admin")) {
			throw new ForbiddenException("Access denied!");
		}
		
		return true;
		
	}
}
