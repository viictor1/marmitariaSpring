package com.victortavin.marmitaria.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.dtos.role.RoleInsertDto;
import com.victortavin.marmitaria.service.RoleService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

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
	public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleInsertDto roleInsertDto) {
		validator.validateAdmin();
		
		RoleDto roleDto = service.addRole(roleInsertDto);
		
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
	
	@GetMapping
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Roles", description = "Selecionar todas as roles")
	public ResponseEntity<List<RoleDto>> getRoles(){
		validator.validateAdmin();
		List<RoleDto> roles = service.getRoles();
		return ResponseEntity.ok().body(roles);
	}
	
	@GetMapping(value = "/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Role By Id", description = "Selecionar uma role pelo Id")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
		validator.validateAdmin();
		RoleDto role = service.getRoleById(id);
		return ResponseEntity.ok().body(role);
	}
	
	@PutMapping(value = "/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update Role", description = "Modificar o nome de uma role pelo id")
	public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody RoleInsertDto insertDto){
		validator.validateAdmin();
		RoleDto roleDto = service.updateRole(id, insertDto);
		return ResponseEntity.ok().body(roleDto);
	}
	
}
