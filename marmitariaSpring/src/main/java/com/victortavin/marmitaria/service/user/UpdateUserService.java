package com.victortavin.marmitaria.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.dtos.user.UserUpdateDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UpdateUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public UserDto update(UserDto userDto, UserUpdateDto updateDto) {
		if(passwordEncoder.matches(updateDto.getOldPassword(), userDto.getPassword()))
		{
			UserEntity userEntity = copyUpdateDtoToEntity(userDto.getEmail(), updateDto);
			userEntity = repository.save(userEntity);
			return new UserDto(userEntity);
		}
		else {
			throw new BadCredentialsException("Senha inválida");
		}
	}
	
	public UserEntity copyUpdateDtoToEntity(String email, UserUpdateDto updateDto) {
		UserEntity user = repository.findByEmail(email);
		
		if(updateDto.getFirstName() != null) {
			user.setFirstName(updateDto.getFirstName());
		}
		
		if(updateDto.getLastName() != null) {
			user.setLastName(updateDto.getLastName());
		}
		
		if(updateDto.getCpf() != null) {
			user.setCpf(updateDto.getCpf());
		}
		
		if(updateDto.getNewPassword() != null) {
			user.setPassword(passwordEncoder.encode(updateDto.getNewPassword()));
		}
		
		return user;
	}
}
