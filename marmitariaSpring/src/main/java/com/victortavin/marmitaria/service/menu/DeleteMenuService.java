package com.victortavin.marmitaria.service.menu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.repositories.MenuRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class DeleteMenuService {


	@Autowired
	private MenuRepository repository;

	@Transactional
	public String deleteMenu(Long id) {	
		checkIfMenuEmptyById(id);
		
		try {
			MenuEntity menuEntity = repository.getReferenceById(id);
			repository.delete(menuEntity);
			return menuEntity.getName();				
		}
		catch (Exception e) {
			throw new DataIntegrityViolationException("");
		}
	}
	
	private void checkIfMenuEmptyById(Long id) {
		Optional<MenuEntity> menuOptional = repository.findById(id);
		
		if(menuOptional.isEmpty()) {
			throw new ResourceNotFoundException("Menu não encontrado");
		}
	}
	
}
