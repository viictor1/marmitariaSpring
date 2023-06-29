package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;

	@Transactional
	public RoleDto addRole(RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		copyRoleDtoToRoleEntity(roleDto, roleEntity);
		
		roleEntity = repository.save(roleEntity);
		
		return new RoleDto(roleEntity);
		
	}
	
	private void copyRoleDtoToRoleEntity(RoleDto roleDto, RoleEntity roleEntity) {
		roleEntity.setName(roleDto.getName());
	}

	public String delete(Long id) {
		try {
			RoleEntity roleEntity = repository.getReferenceById(id);
			repository.delete(roleEntity);
			return roleEntity.getName();
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Role não encontrada ou está em uso");
		}

	}
}
