package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class RoleDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo é name obrigatório")
	private String name;
	
	public RoleDto() {
		
	}

	public RoleDto(Long id, @NotBlank(message = "Campo é name obrigatório") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleDto other = (RoleDto) obj;
		return Objects.equals(id, other.id);
	}
	
}
