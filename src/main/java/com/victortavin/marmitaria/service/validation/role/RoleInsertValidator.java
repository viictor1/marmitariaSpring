package com.victortavin.marmitaria.service.validation.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.victortavin.marmitaria.controllers.exceptions.FieldMessage;
import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleInsertValidator implements ConstraintValidator<RoleInsertValid, RoleDto>{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void initialize(RoleInsertValid ann) {
		
	}

	@Override
	public boolean isValid(RoleDto roleDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<RoleEntity> roleOptional = roleRepository.findByName(roleDto.getName());
		
		RoleEntity roleEntity = roleOptional.orElseThrow(()-> new ResourceNotFoundException("Role not found: "+roleDto.getName()));
		
		if(roleEntity != null) {
			list.add(new FieldMessage("name", "Este nome já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
}
