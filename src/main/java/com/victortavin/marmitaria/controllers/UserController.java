package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.TokenDto;
import com.victortavin.marmitaria.dtos.UserDeleteDto;
import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.dtos.UserLoginDto;
import com.victortavin.marmitaria.dtos.UserUpdateDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.service.MessageService;
import com.victortavin.marmitaria.service.TokenService;
import com.victortavin.marmitaria.service.UserService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
	
	@Autowired
	private MessageService message;
	
	@Tag(name = "Register", description = "Cadastrar um novo usuário")
	@PostMapping(value="/cadastro")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserInsertDto userInsertDto) {
		UserDto userDto = service.addUser(userInsertDto);
		

		message.userRegisteredSuccessfully(userDto.getEmail(), userDto.getFirstName());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
					.buildAndExpand(userInsertDto.getId()).toUri();
			
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@Tag(name = "Login", description = "Fazer login com um usuário existente")
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletRequest request){
		
		var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
		var authentication = authenticationManager.authenticate(authenticationToken);
		
		String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());
		
		String disposito = message.informacoesDoDispositivo(request);
		message.userLogin(userLoginDto.getEmail(), disposito);
		
		return ResponseEntity.ok().body(new TokenDto(token));
	}
	
	@SecurityRequirement(name = "Bearer Authentication")
	@Tag(name = "Find user by ID")
	@GetMapping(value= "/{id}")
	public ResponseEntity<UserDto> findByIdUser(@PathVariable Long id){
		validator.validateAdmin();
		UserDto userDto = service.findByidUser(id);
		
		return ResponseEntity.ok().body(userDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update User", description = 
	"Altera o usuário, se não enviar um valor novo para algum atributo ele manterá o antigo,"
	+ " é necessário confirmar a senha antiga")
	@PutMapping(value = "/update")
	public ResponseEntity<UserDto> update(HttpServletRequest request, @RequestBody UserUpdateDto updateDto){
		String email = retrieveUsernameFromRequest(request);

		UserDto userDto = service.findByEmailUser(email);
		
		userDto = service.update(userDto, updateDto);
		
		return ResponseEntity.ok().body(userDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete User", description = "Deleta o usuário que está logado após confirmar senha")
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> delete(HttpServletRequest request, @RequestBody UserDeleteDto deleteDto){
		String email = retrieveUsernameFromRequest(request);
		UserDto userDto = service.findByEmailUser(email);
		service.delete(userDto, deleteDto.getPassword());
		
		return ResponseEntity.ok().body("User " + userDto.getFirstName() + " foi deletado");
	}
	
	private String retrieveUsernameFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		authorizationHeader = authorizationHeader.replace("Bearer ", "");
		return tokenService.getSubject(authorizationHeader);
	}
}

