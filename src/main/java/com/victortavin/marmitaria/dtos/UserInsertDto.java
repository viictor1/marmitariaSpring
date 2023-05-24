package com.victortavin.marmitaria.dtos;

import java.util.Objects;

import com.victortavin.marmitaria.entities.RoleEntity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserInsertDto extends UserDto{
	private static final long serialVersionUID = 1L;

	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, role);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInsertDto other = (UserInsertDto) obj;
		return Objects.equals(password, other.password) && Objects.equals(role, other.role);
	}
	
	
	
}
