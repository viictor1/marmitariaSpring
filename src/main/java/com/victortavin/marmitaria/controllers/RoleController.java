package com.victortavin.marmitaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@PostMapping
	public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
		roleDto =  service.addRole(roleDto);
		return ResponseEntity.ok().body(roleDto);
	}
}
