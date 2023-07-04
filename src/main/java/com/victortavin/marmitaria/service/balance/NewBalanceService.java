package com.victortavin.marmitaria.service.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.balance.BalanceDto;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.repositories.BalanceRepository;

@Service
public class NewBalanceService {
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	public BalanceDto newBalance() {
		BalanceEntity balanceEntity = new BalanceEntity(null, 0);

		balanceEntity = balanceRepository.save(balanceEntity);
		

		return new BalanceDto(balanceEntity);
	}
}
