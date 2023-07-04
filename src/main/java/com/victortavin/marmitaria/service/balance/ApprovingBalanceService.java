package com.victortavin.marmitaria.service.balance;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.balance.Add_BalanceDto;
import com.victortavin.marmitaria.dtos.balance.BalanceDto;
import com.victortavin.marmitaria.dtos.user.UserBalanceDto;
import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.AddBalanceRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.MessageService;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

@Service
public class ApprovingBalanceService {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddBalanceRepository addBalanceRepository;

	@Autowired
	private MessageService messageService;
	
	@Transactional
	public BalanceDto approvingBalance(UserBalanceDto userBalanceDto) {
		
		UserEntity userEntity = addBalanceisAproved(userBalanceDto);
		
		userRepository.save(userEntity);

		BalanceEntity balanceEntity = userEntity.getBalance();
		
		return new BalanceDto(balanceEntity);
	}
	
	
	private UserEntity addBalanceisAproved(UserBalanceDto userBalanceDto) {
		UserEntity userEntity = findByIdUser(userBalanceDto.getId());
		
		userEntity.getAddBalance().clear();
		
		for (Add_BalanceDto add_BalanceDto : userBalanceDto.getAddBalance()) {
			
			Add_BalanceEntity addBalanceEntity = new Add_BalanceEntity();
			
			copyAddBalanceDtoToAddBalanceEntity(add_BalanceDto, addBalanceEntity);
			
			userEntity.getAddBalance().add(addBalanceEntity);
			
			if (add_BalanceDto.isApproved()) {
				
				userEntity.getBalance().setValue(userEntity.getBalance().getValue() + add_BalanceDto.getAddValue());
				messageService.saldoAdicionado(userEntity.getEmail(), add_BalanceDto.getAddValue(), userEntity.getBalance().getValue());
			}
		}
		
		
		
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
