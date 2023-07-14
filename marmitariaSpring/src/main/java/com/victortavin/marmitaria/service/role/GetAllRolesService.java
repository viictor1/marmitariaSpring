package com.victortavin.marmitaria.service.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class GetAllRolesService {

	@Autowired
	private RoleRepository repository;

	@Transactional
	public List<RoleDto> getRoles() {
		List<RoleDto> dtoList = new ArrayList<RoleDto>();
		List<RoleEntity> entityList = repository.findAll();
		
		for (RoleEntity roleEntity : entityList) {
			dtoList.add(new RoleDto(roleEntity));
		}
		
		return dtoList;
	}
}
