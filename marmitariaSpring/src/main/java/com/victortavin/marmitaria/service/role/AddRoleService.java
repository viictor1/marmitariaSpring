package com.victortavin.marmitaria.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.dtos.role.RoleInsertDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AddRoleService {

	@Autowired
	private RoleRepository repository;

	@Transactional
	public RoleDto addRole(@Valid RoleInsertDto roleInsertDto) {
		RoleEntity roleEntity = new RoleEntity();
		copyRoleInsertDtoToRoleEntity(roleInsertDto, roleEntity);
		
		roleEntity = repository.save(roleEntity);
		
		return new RoleDto(roleEntity);
		
	}
	
	private void copyRoleInsertDtoToRoleEntity (RoleInsertDto insertDto, RoleEntity entity) {
		entity.setName(insertDto.getName());
	}
}
