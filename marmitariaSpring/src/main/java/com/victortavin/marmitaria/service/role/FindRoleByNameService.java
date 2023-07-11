package com.victortavin.marmitaria.service.role;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class FindRoleByNameService {

	@Autowired
	private RoleRepository repository;
	
	@Transactional
	public RoleDto findRoleByName(String name) {
		Optional<RoleEntity> roleOptional = repository.findByName(name);
		
		RoleEntity roleEntity = roleOptional.orElseThrow(()-> new ResourceNotFoundException("Role not found: "+ name));
		
		return new RoleDto(roleEntity);
	}
}
