package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.service.RoleService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@PostMapping
	public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto, HttpServletRequest request) {
		validator.validateAdmin();
		
		roleDto = service.addRole(roleDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(roleDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(roleDto);
	}
}
