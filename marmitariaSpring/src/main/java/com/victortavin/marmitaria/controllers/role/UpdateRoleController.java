package com.victortavin.marmitaria.controllers.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.dtos.role.RoleInsertDto;
import com.victortavin.marmitaria.service.role.UpdateRoleService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class UpdateRoleController {

	@Autowired
	private UpdateRoleService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@PutMapping(value = "/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update Role", description = "Modificar o nome de uma role pelo id")
	public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody RoleInsertDto insertDto){
		validator.validateAdmin();
		RoleDto roleDto = service.updateRole(id, insertDto);
		return ResponseEntity.ok().body(roleDto);
	}
}
