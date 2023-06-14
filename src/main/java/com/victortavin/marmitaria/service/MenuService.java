package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.MenuDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository repository;

	@Transactional
	public MenuDto addMenu(MenuDto menuDto) {
		MenuEntity menuEntity = new MenuEntity();
		copyMenuDtoToMenuEntity(menuDto, menuEntity);
		
		menuEntity = repository.save(menuEntity);
		
		return new MenuDto(menuEntity);
	}
	
	private void copyMenuDtoToMenuEntity(MenuDto menuDto, MenuEntity menuEntity) {
		menuEntity.setName(menuDto.getName());
		menuEntity.setPrice(menuDto.getPrice());
		menuEntity.setDiscount(menuDto.getDiscount());
	}
}
