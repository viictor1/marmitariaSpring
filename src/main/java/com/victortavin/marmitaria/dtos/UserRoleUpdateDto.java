package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleUpdateDto implements Serializable{

	private static final long serialVersionUID = 1L;

	public Long idUser;

	public Long idRole;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public UserRoleUpdateDto() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRole, idUser);
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
		return Objects.equals(idRole, other.idRole) && Objects.equals(idUser, other.idUser);
	}
	
	
}
