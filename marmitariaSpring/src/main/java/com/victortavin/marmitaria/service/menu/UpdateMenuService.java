package com.victortavin.marmitaria.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.dtos.menu.MenuUpdateDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class UpdateMenuService {

	@Autowired
	private MenuRepository repository;

	@Transactional
	public MenuDto update(Long id, MenuUpdateDto menuUpdateDto) {
		MenuEntity menuEntity = repository.getReferenceById(id);
	
		setMenuDtoFromUpdateDto(menuEntity, menuUpdateDto);
		
		menuEntity = repository.save(menuEntity);
		
		return new MenuDto(menuEntity);
	}
	
	private void setMenuDtoFromUpdateDto(MenuEntity menuEntity, MenuUpdateDto menuUpdateDto) {
		if (menuUpdateDto.getName() != null) {
			menuEntity.setName(menuUpdateDto.getName());
		}
		
		if (menuUpdateDto.getPrice() != 0) {
			menuEntity.setPrice(menuUpdateDto.getPrice());
		}
		
		if (menuUpdateDto.getDiscount() != 0) {
			menuEntity.setDiscount(menuUpdateDto.getDiscount());
		}
		
		if (menuUpdateDto.getDescription() != null) {
			menuEntity.setDescription(menuUpdateDto.getDescription());
		}
	}
}
