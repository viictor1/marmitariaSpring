package com.victortavin.marmitaria.dtos.role;

import java.io.Serializable;
import java.util.Objects;

import com.victortavin.marmitaria.service.validation.role.RoleInsertValid;

import jakarta.validation.constraints.NotBlank;

@RoleInsertValid
public class RoleInsertDto implements Serializable{

	private static final long serialVersionUID = 1L;


	@NotBlank(message = "Campo name é obrigatório")
	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public RoleInsertDto() {
		super();
	}


	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleInsertDto other = (RoleInsertDto) obj;
		return Objects.equals(name, other.name);
	}

	
}