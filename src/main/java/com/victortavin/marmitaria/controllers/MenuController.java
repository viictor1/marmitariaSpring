package com.victortavin.marmitaria.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.MenuDto;
import com.victortavin.marmitaria.dtos.MenuInsertDto;
import com.victortavin.marmitaria.dtos.MenuUpdateDto;
import com.victortavin.marmitaria.service.MenuService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuService service;
	
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
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get All Menu", description = "Mostra todos os itens do menu")
	@GetMapping
	public ResponseEntity<List<MenuDto>> getAllMenu(){
		validator.validateAdmin();
		
		List<MenuDto> listMenu = new ArrayList<MenuDto>();
		listMenu = service.getAllMenu();
		return ResponseEntity.ok().body(listMenu);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Menu By Id", description = "Seleciona um item do menu pelo Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id){
		validator.validateAdmin();
		
		MenuDto menuDto = service.getMenuById(id);
		return ResponseEntity.ok().body(menuDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete Menu By Id", description = "Deleta um item do menu pelo Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable Long id){
		String name = service.deleteMenu(id);
		return ResponseEntity.ok().body(name + " foi deletado");
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update Menu", description = 
	"Altera o usuário, se não enviar um valor novo para algum atributo ele manterá o antigo")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<MenuDto> update(@RequestBody MenuUpdateDto menuUpdateDto, @PathVariable Long id){
		MenuDto menuDto = service.update(id, menuUpdateDto);
		
		return ResponseEntity.ok().body(menuDto);
	}
}
