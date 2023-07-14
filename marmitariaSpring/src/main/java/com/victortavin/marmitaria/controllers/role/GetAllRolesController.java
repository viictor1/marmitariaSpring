package com.victortavin.marmitaria.controllers.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.role.RoleDto;
import com.victortavin.marmitaria.service.role.GetAllRolesService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/roles")
public class GetAllRolesController {

	@Autowired
	private GetAllRolesService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@GetMapping
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Roles", description = "Selecionar todas as roles")
	public ResponseEntity<List<RoleDto>> getRoles(){
		validator.validateAdmin();
		List<RoleDto> roles = service.getRoles();
		return ResponseEntity.ok().body(roles);
	}
}
