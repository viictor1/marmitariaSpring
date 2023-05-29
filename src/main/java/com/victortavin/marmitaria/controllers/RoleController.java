package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	//só por o autowired em cima do RoleService que ele já pega o contexto e não precisa daquele outro código
	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<String> oi(){
		return ResponseEntity.ok().body("oii");
	}
	
	@PostMapping
	public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto) {
		roleDto = service.addRole(roleDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(roleDto.getId()).toUri();
		
		//como a tente tem está criando uma role o status tem que ser 201(created) e não 200(ok)
		return ResponseEntity.created(uri).body(roleDto);
	}
}
