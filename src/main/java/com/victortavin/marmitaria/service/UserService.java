package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public UserDto addUser(UserInsertDto userInsertDto) {
		UserEntity userEntity = new UserEntity();
		
		standardizeCpf(userInsertDto);
		copyUserInsertDtoToUserEntity(userInsertDto, userEntity);
		addRoleInUser(userEntity);
		
		userEntity = repository.save(userEntity);
		
		return new UserDto(userEntity);
		
	}
	
	private void standardizeCpf(UserInsertDto userInsertDto) {
		String cpf = userInsertDto.getCpf();
		cpf = cpf.replaceAll("[^0-9]", "");
		userInsertDto.setCpf(cpf);
	}
	
	private void copyUserInsertDtoToUserEntity(UserInsertDto userInsertDto, UserEntity userEntity) {
		userEntity.setFirstName(userInsertDto.getFirstName());
		userEntity.setLastName(userInsertDto.getLastName());
		userEntity.setCpf(userInsertDto.getCpf());
		userEntity.setEmail(userInsertDto.getEmail());
		//criptografa a senha
		userEntity.setPassword(passwordEncoder.encode(userInsertDto.getPassword()));		
	}
	
	private void addRoleInUser(UserEntity userEntity) {
		userEntity.setRole(roleRepository.findByName("User"));
	}
	
}
