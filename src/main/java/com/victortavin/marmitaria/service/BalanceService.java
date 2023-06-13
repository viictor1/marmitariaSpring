package com.victortavin.marmitaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.Add_BalanceDto;
import com.victortavin.marmitaria.dtos.BalanceDto;
import com.victortavin.marmitaria.dtos.UserBalanceDto;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.BalanceRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public BalanceDto newBalance() {
		BalanceEntity balanceEntity = new BalanceEntity(null, 0);
		
		balanceEntity = balanceRepository.save(balanceEntity);
		
		return new BalanceDto(balanceEntity);
	}

	@Transactional(readOnly = true)
	public Page<UserBalanceDto> findAllPage(Pageable pageable) {
		Page<UserEntity> list = userRepository.findAll(pageable);
		
		return list.map(x -> new UserBalanceDto(x));
	}

	public BalanceDto aprovedBalance(UserBalanceDto userBalanceDto) {
		float balance = 0;
		for (Add_BalanceDto add_BalanceDto : userBalanceDto.getAddBalance()) {
			if(add_BalanceDto.isApproved()) {
				
				balance += add_BalanceDto.getAddValue();
				
			}
		}
		
		Optional<UserEntity> userOptional = userRepository.findById(userBalanceDto.getId());
		
		UserEntity userEntity = userOptional.orElseThrow(()-> new ResourceNotFoundException("Id not found: " + userBalanceDto.getId()));
		
		userEntity.getBalance().setValue(balance);
		
		userRepository.save(userEntity);
		
		BalanceEntity balanceEntity = userEntity.getBalance();
		return new BalanceDto(balanceEntity);
	}
	
}
