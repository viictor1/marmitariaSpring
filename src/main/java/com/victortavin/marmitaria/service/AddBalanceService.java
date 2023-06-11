package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.Add_BalanceDto;
import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.AddBalanceRepository;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AddBalanceService {
	
	@Autowired
	private AddBalanceRepository addBalanceRepository;
	
	@Autowired
	private UserRepository userRepository; 

	@Transactional
	public Add_BalanceDto newBalance (float addValue) {
		Add_BalanceEntity addBalanceEntity = new Add_BalanceEntity(null, addValue, false, recuperarUser());
		
		addBalanceEntity = addBalanceRepository.save(addBalanceEntity);
		
		return new Add_BalanceDto(addBalanceEntity); 
		
	}
	
	private UserEntity recuperarUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserEntity userEntity = (UserEntity) principal;
		
		return userEntity;
	}
	
}
