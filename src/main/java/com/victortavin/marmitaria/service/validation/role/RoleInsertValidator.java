package com.victortavin.marmitaria.service.validation.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.victortavin.marmitaria.controllers.exceptions.FieldMessage;
import com.victortavin.marmitaria.dtos.RoleInsertDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleInsertValidator implements ConstraintValidator<RoleInsertValid, RoleInsertDto>{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void initialize(RoleInsertValid ann) {
		
	}

	@Override
	public boolean isValid(RoleInsertDto roleDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		RoleEntity roleEntity = roleRepository.findByName(roleDto.getName());
		
		if(roleEntity != null) {
			list.add(new FieldMessage("name", "Já existe uma role com esse nome"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
}
