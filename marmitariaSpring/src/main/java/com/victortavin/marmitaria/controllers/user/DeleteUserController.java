package com.victortavin.marmitaria.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.user.UserDeleteDto;
import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.service.TokenService;
import com.victortavin.marmitaria.service.user.DeleteUserService;
import com.victortavin.marmitaria.service.user.FindByEmailUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class DeleteUserController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private FindByEmailUserService emailService;
	
	@Autowired
	private DeleteUserService service;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Delete User", description = "Deleta o usuário que está logado após confirmar senha")
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> delete(HttpServletRequest request, @RequestBody UserDeleteDto deleteDto){
		String email = retrieveUsernameFromRequest(request);
		UserDto userDto = emailService.findByEmailUser(email);
		service.delete(userDto, deleteDto.getPassword());
		
		return ResponseEntity.ok().body("User " + userDto.getFirstName() + " foi deletado");
	}
	
	private String retrieveUsernameFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		authorizationHeader = authorizationHeader.replace("Bearer ", "");
		return tokenService.getSubject(authorizationHeader);
	}
}
