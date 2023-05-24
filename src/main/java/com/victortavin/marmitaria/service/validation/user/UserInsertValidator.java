package com.victortavin.marmitaria.service.validation.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.victortavin.marmitaria.controllers.exceptions.FieldMessage;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UserInsertValid ann) {
		
	}

	@Override
	public boolean isValid(UserInsertDto userInsertDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		UserEntity userEntityEmail = userRepository.findByEmail(userInsertDto.getEmail());
		
		if(userEntityEmail != null) {
			list.add(new FieldMessage("email", "Este email já existe"));
		}
		
		UserEntity userEntityCpf = userRepository.findByCpf(userInsertDto.getCpf());
		
		if(userEntityCpf != null) {
			list.add(new FieldMessage("cpf", "Este cpf já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
}
