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
import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.AddBalanceRepository;
import com.victortavin.marmitaria.repositories.BalanceRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddBalanceRepository addBalanceRepository;

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

	@Transactional
	public BalanceDto aprovedBalance(UserBalanceDto userBalanceDto) {
		
		UserEntity userEntity = addBalanceisAproved(userBalanceDto);
		
		userRepository.save(userEntity);

		BalanceEntity balanceEntity = userEntity.getBalance();
		
		return new BalanceDto(balanceEntity);
	}
	
	private UserEntity addBalanceisAproved(UserBalanceDto userBalanceDto) {
		UserEntity userEntity = findByIdUser(userBalanceDto.getId());
		
		float balance = 0;
		
		userEntity.getAddBalance().clear();
		
		for (Add_BalanceDto add_BalanceDto : userBalanceDto.getAddBalance()) {
			
			Add_BalanceEntity addBalanceEntity = new Add_BalanceEntity();
			
			copyAddBalanceDtoToAddBalanceEntity(add_BalanceDto, addBalanceEntity);
			
			userEntity.getAddBalance().add(addBalanceEntity);
			
			if (add_BalanceDto.isApproved()) {

				balance += add_BalanceDto.getAddValue();

			}
		}
		
		userEntity.getBalance().setValue(balance);
		
		return userEntity;
	}
	
	private UserEntity findByIdUser(Long id) {
		Optional<UserEntity> userOptional = userRepository.findById(id);

		UserEntity userEntity = userOptional
				.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
		
		return userEntity;
	}
	
	private void copyAddBalanceDtoToAddBalanceEntity(Add_BalanceDto add_BalanceDto, Add_BalanceEntity addBalanceEntity) {
		 addBalanceEntity = addBalanceRepository.getReferenceById(add_BalanceDto.getId());
		
		addBalanceEntity.setApproved(add_BalanceDto.isApproved());
	
	}

}
