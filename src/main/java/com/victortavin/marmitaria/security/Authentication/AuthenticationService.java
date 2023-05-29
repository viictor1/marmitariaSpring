package com.victortavin.marmitaria.security.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.UserDto;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.security.config.JwtService;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	public BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public UserDto register(RegisterRequest request) {
		UserEntity user = new UserEntity();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setCpf(request.getCpf());
		user.setEmail(request.getEmail());
		user.setPassword(bCrypt.encode(request.getPassword()));
		user.setRole(roleRepository.findByName("USER"));
		
		user = userRepository.save(user);
		jwtService.generateToken(user);
		return new UserDto(user);
	}

	public AuthenticationResponse login(AuthenticationRequest request) {
		authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserEntity user = userRepository.findByEmail(request.getEmail());
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
}
