package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.RoleDto;
import com.victortavin.marmitaria.service.RoleService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@PostMapping
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Add Role", description = "Criar role nova")
	public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto) {
		validator.validateAdmin();
		
		roleDto = service.addRole(roleDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(roleDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(roleDto);
	}
	
	@DeleteMapping(value="/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete Role", description = "Deleta uma role pelo id")
	public ResponseEntity<String> deleteRole(@PathVariable Long id){
		validator.validateAdmin();
		String roleName = service.delete(id);
		return ResponseEntity.ok().body("Role " + roleName + " Deletada");
	}
	
}
