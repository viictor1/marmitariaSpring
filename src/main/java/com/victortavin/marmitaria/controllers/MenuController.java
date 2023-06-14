package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.MenuDto;
import com.victortavin.marmitaria.service.MenuService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@PostMapping
	public ResponseEntity<MenuDto> addMenu(@Valid @RequestBody MenuDto menuDto){
		validator.validateAdmin();
		
		menuDto = service.addMenu(menuDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(menuDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(menuDto);
	}
}
