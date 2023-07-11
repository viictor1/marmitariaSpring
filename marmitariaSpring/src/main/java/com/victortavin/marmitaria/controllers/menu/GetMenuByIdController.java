package com.victortavin.marmitaria.controllers.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.service.menu.GetMenuByIdService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/menu")
public class GetMenuByIdController {

	@Autowired
	private GetMenuByIdService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Menu By Id", description = "Seleciona um item do menu pelo Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id){
		validator.validateAdmin();
		
		MenuDto menuDto = service.getMenuById(id);
		return ResponseEntity.ok().body(menuDto);
	}
}
