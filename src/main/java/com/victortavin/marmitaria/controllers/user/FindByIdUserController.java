package com.victortavin.marmitaria.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.service.user.FindByIdUserService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users")
public class FindByIdUserController {
	
	@Autowired
	private FindByIdUserService service;
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Find user by ID")
	@GetMapping(value= "/{id}")
	public ResponseEntity<UserDto> findByIdUser(@PathVariable Long id){
		validator.validateAdmin();
		UserDto userDto = service.findByidUser(id);
		
		return ResponseEntity.ok().body(userDto);
	}
}
