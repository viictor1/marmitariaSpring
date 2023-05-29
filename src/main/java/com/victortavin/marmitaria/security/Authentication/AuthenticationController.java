package com.victortavin.marmitaria.security.Authentication;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.UserDto;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request){
		UserDto userDto = authService.register(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(request.getId()).toUri();
		
		return ResponseEntity.created(uri).body(userDto);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
}
