package com.victortavin.marmitaria.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.MessageDto;
import com.victortavin.marmitaria.entities.MessageEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;
	
	@Transactional(readOnly = true)
	public List<MessageDto> findAllPage(){
		String email = recuperandoEmail();
		
		
		
		List<MessageEntity> list = repository.findAllByRecipient(email);
		
		for (MessageEntity messageEntity : list) {
			System.out.println("teste" + messageEntity);
		}
		
		return list.stream().map(MessageDto::new).collect(Collectors.toList());
	}
	
	private String recuperandoEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
		    UserEntity user = (UserEntity) authentication.getPrincipal();
		    // Faça o que precisar com o objeto User recuperado
		    return user.getEmail();
		}
		return null;
	}
}
