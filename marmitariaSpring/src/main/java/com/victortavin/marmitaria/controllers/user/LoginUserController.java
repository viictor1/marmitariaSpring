package com.victortavin.marmitaria.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.token.TokenDto;
import com.victortavin.marmitaria.dtos.user.UserLoginDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.service.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class LoginUserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Tag(name = "Login", description = "Fazer login com um usuário existente")
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletRequest request){
		
		var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
		var authentication = authenticationManager.authenticate(authenticationToken);
		
		String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());
		
		//String disposito = message.informacoesDoDispositivo(request);
		//message.userLogin(userLoginDto.getEmail(), disposito);
		
		return ResponseEntity.ok().body(new TokenDto(token));
	}
}
