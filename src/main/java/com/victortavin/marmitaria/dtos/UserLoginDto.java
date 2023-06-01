package com.victortavin.marmitaria.dtos;

import java.io.Serializable;

import com.victortavin.marmitaria.entities.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Email(message = "este campo precisa ser um email")
	@NotBlank(message = "campo email é obrigatório")
	private String email;
	
	@NotBlank(message = "campo password é obrigatório")
	private String password;
	
	public UserLoginDto() {
		
	}

	public UserLoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserLoginDto(UserEntity userEntity) {
		super();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
