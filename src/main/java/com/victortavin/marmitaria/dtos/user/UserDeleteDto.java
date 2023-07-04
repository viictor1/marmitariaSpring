package com.victortavin.marmitaria.dtos.user;

import java.io.Serializable;
import java.util.Objects;

public class UserDeleteDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDeleteDto() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDeleteDto other = (UserDeleteDto) obj;
		return Objects.equals(password, other.password);
	}
	
	
}
