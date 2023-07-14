package com.victortavin.marmitaria.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.user.UserDto;
import com.victortavin.marmitaria.dtos.user.UserUpdateDto;
import com.victortavin.marmitaria.service.TokenService;
import com.victortavin.marmitaria.service.user.FindByEmailUserService;
import com.victortavin.marmitaria.service.user.UpdateUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UpdateUserController {
	
	@Autowired
	private UpdateUserService service;
	
	@Autowired
	private FindByEmailUserService emailService;
	
	@Autowired
	private TokenService tokenService;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update User", description = 
	"Altera o usuário, se não enviar um valor novo para algum atributo ele manterá o antigo,"
	+ " é necessário confirmar a senha antiga")
	@PutMapping(value = "/update")
	public ResponseEntity<UserDto> update(HttpServletRequest request, @RequestBody UserUpdateDto updateDto){
		String email = retrieveUsernameFromRequest(request);

		UserDto userDto = emailService.findByEmailUser(email);
		
		userDto = service.update(userDto, updateDto);
		
		return ResponseEntity.ok().body(userDto);
	}
	
	private String retrieveUsernameFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		authorizationHeader = authorizationHeader.replace("Bearer ", "");
		return tokenService.getSubject(authorizationHeader);
	}
}
