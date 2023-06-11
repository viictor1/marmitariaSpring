package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.BalanceDto;
import com.victortavin.marmitaria.dtos.UserBalanceDto;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.BalanceRepository;
import com.victortavin.marmitaria.repositories.UserRepository;

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
}
