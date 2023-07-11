package com.victortavin.marmitaria.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.dtos.menu.MenuInsertDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class AddMenuService {

	@Autowired
	private MenuRepository repository;

	@Transactional
	public MenuDto addMenu(MenuInsertDto menuDto) {
		MenuEntity menuEntity = new MenuEntity();
		copyMenuInsertDtoToMenuEntity(menuDto, menuEntity);
		
		menuEntity = repository.save(menuEntity);
		
		return new MenuDto(menuEntity);
	}
	
	private void copyMenuInsertDtoToMenuEntity(MenuInsertDto menuDto, MenuEntity menuEntity) {
		menuEntity.setName(menuDto.getName());
		menuEntity.setPrice(menuDto.getPrice());
		menuEntity.setDiscount(menuDto.getDiscount());
		menuEntity.setActive(false);
		menuEntity.setDescription(menuDto.getDescription());
	}
}
