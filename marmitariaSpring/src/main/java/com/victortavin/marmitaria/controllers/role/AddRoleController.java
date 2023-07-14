package com.victortavin.marmitaria.controllers.role;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.dtos.role.RoleInsertDto;
import com.victortavin.marmitaria.service.role.AddRoleService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class AddRoleController {

	@Autowired
	private AddRoleService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@PostMapping
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Add Role", description = "Criar role nova")
	public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleInsertDto roleInsertDto) {
		validator.validateAdmin();
		
		RoleDto roleDto = service.addRole(roleInsertDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(roleDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(roleDto);
	}
}
