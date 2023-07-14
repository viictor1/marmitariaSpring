package com.victortavin.marmitaria.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class GetRoleByIdService {

	@Autowired
	private RoleRepository repository;
	
	@Transactional
	public RoleDto getRoleById(Long id) {
		try {
			RoleEntity entity = repository.getReferenceById(id);
			return new RoleDto(entity);
		}catch (Exception e) {
			throw new ResourceNotFoundException("Role não encontrada");
		}
	}
}
