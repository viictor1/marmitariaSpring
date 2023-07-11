package com.victortavin.marmitaria.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service 
public class GetMenuByIdService {
	@Autowired
	private MenuRepository repository;

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
}
