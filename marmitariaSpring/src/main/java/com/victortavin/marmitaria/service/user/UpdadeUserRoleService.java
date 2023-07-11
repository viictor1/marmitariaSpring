package com.victortavin.marmitaria.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

public class UpdadeUserRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public List<String> updateUserRole(Long idUser, String roleName) {
		try {
			UserEntity userEntity = repository.getReferenceById(idUser);	
			Optional<RoleEntity> roleOptional = roleRepository.findByName(roleName);
			
			RoleEntity roleEntity = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Esse role não existe"));
			
			userEntity.setRole(roleEntity);
			repository.save(userEntity);
			
			List<String> names = new ArrayList<String>();
			names.add(userEntity.getFirstName());
			names.add(roleEntity.getName());
			return names;
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("User ou Role não encontrados");
		}
		
	}
}
