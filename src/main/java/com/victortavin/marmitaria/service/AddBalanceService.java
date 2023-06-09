package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.Add_BalanceDto;
import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.repositories.AddBalanceRepository;

import jakarta.transaction.Transactional;

@Service
public class AddBalanceService {
	
	@Autowired
	private AddBalanceRepository addBalanceRepository;

	@Transactional
	public Add_BalanceDto newBalance (float addValue) {
		return null;
	}
}
