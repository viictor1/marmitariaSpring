package com.victortavin.marmitaria.service.role;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class DeleteRoleService {

	@Autowired
	private RoleRepository repository;

	@Transactional
	public String delete(Long id){
		checkIfRoleEmptyById(id);

		try {
			RoleEntity roleEntity = repository.getReferenceById(id);
			repository.delete(roleEntity);
			return roleEntity.getName();			
		}
		catch (Exception e) {
			throw new DataIntegrityViolationException("");
		}
		
	}
	
	private void checkIfRoleEmptyById(Long id) {
		Optional<RoleEntity> roleOptional = repository.findById(id);

		if(roleOptional.isEmpty()) {
			throw new ResourceNotFoundException("Role não encontrada");
		}
	}
}
