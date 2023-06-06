package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.TokenDto;
import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.dtos.UserLoginDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.service.TokenService;
import com.victortavin.marmitaria.service.UserService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	public UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@PostMapping(value="/cadastro")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserInsertDto userInsertDto) {
		UserDto userDto = service.addUser(userInsertDto);
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
					.buildAndExpand(userInsertDto.getId()).toUri();
			
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto){
		
		var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
		var authentication = authenticationManager.authenticate(authenticationToken);
		
		String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());
		
		return ResponseEntity.ok().body(new TokenDto(token));
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<UserDto> findByIdUser(@PathVariable Long id){
		validator.validateAdmin();
		UserDto userDto = service.findByidUser(id);
		
		return ResponseEntity.ok().body(userDto);
	}
}

