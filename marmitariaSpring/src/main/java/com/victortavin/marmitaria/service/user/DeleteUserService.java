package com.victortavin.marmitaria.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DeleteUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Transactional
	public void delete(UserDto userDto, String password) {
		if(passwordEncoder.matches(password, userDto.getPassword())) {
			repository.deleteById(userDto.getId());
		}
		else {
			throw new BadCredentialsException("Senha inválida");
		}
		
	}

}
