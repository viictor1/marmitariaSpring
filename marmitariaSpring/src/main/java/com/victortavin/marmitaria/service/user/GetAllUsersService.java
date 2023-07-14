package com.victortavin.marmitaria.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class GetAllUsersService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public List<UserDto> getAllUsers() {
		List<UserEntity> entityList= repository.findAll();
		List<UserDto> dtoList = new ArrayList<UserDto>();
		for (UserEntity userEntity : entityList) {
			dtoList.add(new UserDto(userEntity));
		}
		return dtoList;
	}
}
