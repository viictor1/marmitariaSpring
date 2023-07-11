package com.victortavin.marmitaria.service.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class GetAllMenuService {
	@Autowired
	private MenuRepository repository;
	
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
}
