package com.victortavin.marmitaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	private final RoleService service;
	
	@Autowired
	public RoleController(RoleService RoleService) {
		this.service = RoleService;
	}
	
	@PostMapping
	public ResponseEntity<String> addRole(@RequestBody RoleEntity r) {
		service.addRole(r);
		return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
	}
}
