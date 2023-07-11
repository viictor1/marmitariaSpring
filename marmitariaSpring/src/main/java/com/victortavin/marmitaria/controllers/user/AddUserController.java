package com.victortavin.marmitaria.controllers.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.dtos.user.UserInsertDto;
import com.victortavin.marmitaria.service.user.AddUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class AddUserController {
	
	@Autowired
	private AddUserService service;
	
	@Tag(name = "Register", description = "Cadastrar um novo usuário")
	@PostMapping(value="/cadastro")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserInsertDto userInsertDto) {
		UserDto userDto = service.addUser(userInsertDto);
		

		//message.userRegisteredSuccessfully(userDto.getEmail(), userDto.getFirstName());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
					.buildAndExpand(userInsertDto.getId()).toUri();
			
		return ResponseEntity.created(uri).body(userDto);
	}
}
