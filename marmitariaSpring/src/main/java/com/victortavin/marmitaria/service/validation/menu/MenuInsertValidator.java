package com.victortavin.marmitaria.service.validation.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.victortavin.marmitaria.controllers.exceptions.FieldMessage;
import com.victortavin.marmitaria.dtos.menu.MenuInsertDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MenuInsertValidator implements ConstraintValidator<MenuInsertValid, MenuInsertDto>{

	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public void initialize(MenuInsertValid ann) {
		
	}
	
	@Override
	public boolean isValid(MenuInsertDto menuDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		MenuEntity menuEntity = menuRepository.findByName(menuDto.getName());
		
		if(menuEntity != null) {
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
