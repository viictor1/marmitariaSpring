package com.victortavin.marmitaria.dtos;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.service.validation.user.UserInsertValid;

@UserInsertValid
public class UserInsertDto extends UserDto{
	private static final long serialVersionUID = 1L;

	private String password;
	private RoleEntity role;

	public UserInsertDto() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
}
