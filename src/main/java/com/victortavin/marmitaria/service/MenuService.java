package com.victortavin.marmitaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.MenuDto;
import com.victortavin.marmitaria.dtos.MenuInsertDto;
import com.victortavin.marmitaria.dtos.MenuUpdateDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class MenuService {
	
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
	}
	
	@Transactional
	public List<MenuDto> getAllMenu() {
		List<MenuEntity> listEntity = new ArrayList<MenuEntity>();
		List<MenuDto> listDto = new ArrayList<MenuDto>();
		listEntity = repository.findAll();
		
		for (MenuEntity menuEntity : listEntity) {
			listDto.add(new MenuDto(menuEntity));
		}
		return listDto;
	}

	@Transactional
	public MenuDto getMenuById(Long id) {
		try {
			MenuEntity menuEntity = repository.getReferenceById(id);
			return new MenuDto(menuEntity);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Menu não encontrado");
		}
		
	}

	@Transactional
	public String deleteMenu(Long id) {
		try {
			MenuEntity menuEntity = repository.getReferenceById(id);
			repository.delete(menuEntity);
			return menuEntity.getName();
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Menu não encontrado");
		}
	}

	@Transactional
	public MenuDto update(Long id, MenuUpdateDto menuUpdateDto) {
		MenuEntity menuEntity = repository.getReferenceById(id);
		
		if (menuUpdateDto.getName() != null) {
			menuEntity.setName(menuUpdateDto.getName());
		}
		
		if (menuUpdateDto.getPrice() != 0) {
			menuEntity.setPrice(menuUpdateDto.getPrice());
		}
		
		if (menuUpdateDto.getDiscount() != 0) {
			menuEntity.setDiscount(menuUpdateDto.getDiscount());
		}
		
		menuEntity = repository.save(menuEntity);
		
		return new MenuDto(menuEntity);
	}
}
