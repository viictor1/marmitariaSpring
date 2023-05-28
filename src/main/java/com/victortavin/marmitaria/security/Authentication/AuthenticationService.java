package com.victortavin.marmitaria.security.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	private final JwtService jwtService = new JwtService();
	
	private final PasswordEncoder passwordEncoder = null;
	
	private final AuthenticationManager authenticationManager = null;

	public AuthenticationResponse register(RegisterRequest request) {
		UserEntity user = new UserEntity();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setCpf(request.getCpf());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(roleRepository.findByName("USER"));
		
		userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse login(AuthenticationRequest request) {
		authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail());
		
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
}
