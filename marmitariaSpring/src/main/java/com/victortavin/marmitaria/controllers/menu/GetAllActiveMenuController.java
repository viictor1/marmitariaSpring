package com.victortavin.marmitaria.controllers.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.service.menu.GetAllActiveMenuService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/menu")
public class GetAllActiveMenuController {
	@Autowired
	private GetAllActiveMenuService service;
	
	@Autowired
	UserAuthorityValidator validator;

	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get All Active Menu", description = "Mostra todos os itens ativos do menu")
	@GetMapping(value="/active")
	public ResponseEntity<List<MenuDto>> getAllActiveMenu(){
		validator.validateAdmin();
		
		List<MenuDto> listMenu = new ArrayList<MenuDto>();
		listMenu = service.findAllActiveMenu();
		return ResponseEntity.ok().body(listMenu);
	}
}
