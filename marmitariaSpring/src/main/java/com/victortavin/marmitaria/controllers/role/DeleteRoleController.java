package com.victortavin.marmitaria.controllers.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.service.role.DeleteRoleService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/roles")
public class DeleteRoleController {

	@Autowired
	private DeleteRoleService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@DeleteMapping(value="/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete Role", description = "Deleta uma role pelo id")
	public ResponseEntity<String> deleteRole(@PathVariable Long id){
		validator.validateAdmin();
		String roleName = service.delete(id);
		return ResponseEntity.ok().body("Role " + roleName + " Deletada");
	}
}
