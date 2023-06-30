package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleUpdateDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idUser;

	private String roleName;

	public Long getIdUser() {
		return idUser;
	}
	

	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public UserRoleUpdateDto() {
		super();
	}


	@Override
	public int hashCode() {
		return Objects.hash(idUser, roleName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleUpdateDto other = (UserRoleUpdateDto) obj;
		return Objects.equals(idUser, other.idUser) && Objects.equals(roleName, other.roleName);
	}

	
}
