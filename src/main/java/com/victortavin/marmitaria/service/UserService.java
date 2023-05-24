package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional
	public UserDto addUser(UserInsertDto userInsert) {
		UserEntity userEntity = new UserEntity();
		copyUserInsertDtoToUserEntity(userInsert, userEntity);
		
		userEntity = repository.save(userEntity);
		
		return new UserDto(userEntity);
		
	}
	
	private void copyUserInsertDtoToUserEntity(UserInsertDto userInsert, UserEntity userEntity) {
		userEntity.setFirstName(userInsert.getFirstName());
		userEntity.setLastName(userInsert.getLastName());
		userEntity.setCpf(userInsert.getCpf());
		userEntity.setEmail(userInsert.getEmail());
		userEntity.setPassword(userInsert.getPassword());
		}
}
