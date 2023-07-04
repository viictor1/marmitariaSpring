package com.victortavin.marmitaria.dtos.user;

import com.victortavin.marmitaria.service.validation.user.UserInsertValid;

import jakarta.validation.constraints.NotBlank;

@UserInsertValid
public class UserInsertDto extends UserDto{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "campo senha é obrigatório")
	private String password;

	public UserInsertDto() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
