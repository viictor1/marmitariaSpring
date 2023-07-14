package com.victortavin.marmitaria.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.balance.BalanceDto;
import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.dtos.user.UserInsertDto;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.balance.NewBalanceService;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class AddUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private NewBalanceService newBalanceService; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public UserDto addUser(UserInsertDto userInsertDto) {
		UserEntity userEntity = new UserEntity();
		
		standardizeCpf(userInsertDto);
		copyUserInsertDtoToUserEntity(userInsertDto, userEntity);
		addRoleInUser(userEntity);
		addBalanceInUser(userEntity);
		
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
		Optional<RoleEntity> roleOptional = roleRepository.findByName("User");
		
		RoleEntity roleEntity = roleOptional.orElseThrow(()-> new ResourceNotFoundException("Role not found: User"));
		
		userEntity.setRole(roleEntity);
	}
	
	private void addBalanceInUser(UserEntity userEntity) {
		BalanceDto balanceDto = newBalanceService.newBalance();
		
		BalanceEntity balanceEntity = new BalanceEntity(balanceDto.getId(), balanceDto.getValue());
		
		userEntity.setBalance(balanceEntity);
		System.out.println(userEntity.getBalance().getId());
	
	}
}
