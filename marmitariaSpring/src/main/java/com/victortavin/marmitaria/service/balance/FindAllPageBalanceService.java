package com.victortavin.marmitaria.service.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.user.UserBalanceDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;

@Service
public class FindAllPageBalanceService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<UserBalanceDto> findAllPage(Pageable pageable) {
		Page<UserEntity> list = userRepository.findAll(pageable);

		return list.map(x -> new UserBalanceDto(x));
	}
}
