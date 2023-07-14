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
public class UpdateRoleService {

	@Autowired
	private RoleRepository repository;

	@Transactional
	public RoleDto updateRole(Long id, @Valid RoleInsertDto insertDto) {
		RoleEntity entity = repository.getReferenceById(id);
		copyRoleInsertDtoToRoleEntity(insertDto, entity);
		entity = repository.save(entity);
		return new RoleDto(entity);
	}
	
	private void copyRoleInsertDtoToRoleEntity (RoleInsertDto insertDto, RoleEntity entity) {
		entity.setName(insertDto.getName());
	}
}
