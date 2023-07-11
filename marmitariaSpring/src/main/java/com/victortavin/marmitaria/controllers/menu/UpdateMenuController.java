package com.victortavin.marmitaria.controllers.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.menu.MenuDto;
import com.victortavin.marmitaria.dtos.menu.MenuUpdateDto;
import com.victortavin.marmitaria.service.menu.UpdateMenuService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/menu")
public class UpdateMenuController {
	
	@Autowired
	private UpdateMenuService service;
	
	@Autowired
	UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update Menu", description = 
	"Altera o usuário, se não enviar um valor novo para algum atributo ele manterá o antigo")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<MenuDto> update(@RequestBody MenuUpdateDto menuUpdateDto, @PathVariable Long id){
		MenuDto menuDto = service.update(id, menuUpdateDto);
		
		return ResponseEntity.ok().body(menuDto);
	}
}
