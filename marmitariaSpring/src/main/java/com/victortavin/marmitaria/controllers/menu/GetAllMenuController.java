package com.victortavin.marmitaria.controllers.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.service.menu.GetAllMenuService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/menu")
public class GetAllMenuController {

	@Autowired
	private GetAllMenuService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get All Menu", description = "Mostra todos os itens do menu")
	@GetMapping
	public ResponseEntity<List<MenuDto>> getAllMenu(){
		validator.validateAdmin();
		
		List<MenuDto> listMenu = new ArrayList<MenuDto>();
		listMenu = service.getAllMenu();
		return ResponseEntity.ok().body(listMenu);
	}
}
