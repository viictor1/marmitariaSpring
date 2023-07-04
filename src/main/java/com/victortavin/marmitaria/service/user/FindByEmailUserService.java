package com.victortavin.marmitaria.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class FindByEmailUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public UserDto findByEmailUser(String email) {
		Optional<UserEntity> userOptional = Optional.of(repository.findByEmail(email));
		
		UserEntity userEntity = userOptional.orElseThrow(()-> new ResourceNotFoundException("Email not found: " + email));
		UserDto userDto = new UserDto(userEntity);
		userDto.setPassword(userEntity.getPassword());  // fiz isso pq preciso da senha ao fazer o update
		return userDto;
	}
}
