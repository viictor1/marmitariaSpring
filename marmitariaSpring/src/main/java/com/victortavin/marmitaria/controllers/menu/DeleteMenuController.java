package com.victortavin.marmitaria.controllers.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.service.menu.DeleteMenuService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/menu")
public class DeleteMenuController {

	@Autowired
	private DeleteMenuService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete Menu By Id", description = "Deleta um item do menu pelo Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable Long id){
		String name = service.deleteMenu(id);
		return ResponseEntity.ok().body(name + " foi deletado");
	}
}
