package com.victortavin.marmitaria.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.service.user.GetAllUsersService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users")
public class GetAllUsersController {

	@Autowired
	private GetAllUsersService service;
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Get Users", description = "Mostra todos os usuários")
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(){
		validator.validateAdmin();
		return ResponseEntity.ok().body(service.getAllUsers());
	}
}
