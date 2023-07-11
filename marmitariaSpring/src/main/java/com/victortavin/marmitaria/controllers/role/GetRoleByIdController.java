package com.victortavin.marmitaria.controllers.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.service.role.GetRoleByIdService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/roles")
public class GetRoleByIdController {

	@Autowired
	private GetRoleByIdService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@GetMapping(value = "/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Role By Id", description = "Selecionar uma role pelo Id")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
		validator.validateAdmin();
		RoleDto role = service.getRoleById(id);
		return ResponseEntity.ok().body(role);
	}
}
