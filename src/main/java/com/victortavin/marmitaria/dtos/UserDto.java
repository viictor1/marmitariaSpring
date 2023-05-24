package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.victortavin.marmitaria.entities.UserEntity;

import jakarta.validation.constraints.NotBlank;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo First Name é obrigatório")
	private String firstName;
	
	@NotBlank(message = "Campo Last Name é obrigatório")
	private String lastName;
	
	@NotBlank(message = "Campo cpf é obrigarório")
	private String cpf;
	
	@NotBlank(message = "Campo email é obrigatório")
	private String email;

	public UserDto(Long id, String firstName, String lastName, String cpf, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.email = email;
	}
	
	public UserDto(UserEntity userEntity) {
		super();
		this.id = userEntity.getId();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.cpf = userEntity.getCpf();
		this.email = userEntity.getEmail();
	}

	public UserDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}
	
}
