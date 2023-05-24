package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleService {
	private final RoleRepository repository;
	
	@Autowired
	public RoleService(RoleRepository RoleRepository) {
		this.repository = RoleRepository;
	}
	
	@Transactional
	public RoleEntity addRole(RoleEntity r) {
		r = repository.save(r);
		return r;
	}
}
