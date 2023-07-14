package com.victortavin.marmitaria.controllers.menu;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.dtos.menu.MenuInsertDto;
import com.victortavin.marmitaria.service.menu.AddMenuService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/menu")
public class AddMenuController {

	@Autowired
	private AddMenuService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Add Menu", description = "Adcionar item ao menu")
	@PostMapping
	public ResponseEntity<MenuDto> addMenu(@Valid @RequestBody MenuInsertDto menuInsertDto){
		validator.validateAdmin();
		
		MenuDto menuDto = service.addMenu(menuInsertDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(menuDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(menuDto);
	}
}
