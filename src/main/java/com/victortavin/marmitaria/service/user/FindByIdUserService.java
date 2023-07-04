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
public class FindByIdUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public UserDto findByidUser(Long id) {
		Optional<UserEntity> userOptional = repository.findById(id);
		
		UserEntity userEntity = userOptional.orElseThrow(()-> new ResourceNotFoundException("Id not found: " + id));
		
		return new UserDto(userEntity);
	}
}
