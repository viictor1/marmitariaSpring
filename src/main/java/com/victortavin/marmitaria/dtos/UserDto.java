package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo First Name é obrigatório")
	private String firstName;
	
	@NotBlank(message = "Campo Last Name é obrigatório")
	private String lastName;
	
	@Pattern(regexp = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})", message = "Este valor não é um cpf")
	private String cpf;
	
	@Email(message = "Esse campo precisa ser um Email")
	private String email;
	
	private RoleEntity role;

	public UserDto(Long id, String firstName,String lastName, String cpf,String email, RoleEntity role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.email = email;
		this.role = role;
	}

	public UserDto(UserEntity userEntity) {
		super();
		this.id = userEntity.getId();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.cpf = userEntity.getCpf();
		this.email = userEntity.getEmail();
		this.role = userEntity.getRole();
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
	
	
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
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
