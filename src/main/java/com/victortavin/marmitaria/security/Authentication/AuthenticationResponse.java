package com.victortavin.marmitaria.security.Authentication;


public class AuthenticationResponse {
	private String token;
	
	public AuthenticationResponse(String jwtToken) {
		this.token = jwtToken;
	}
}
