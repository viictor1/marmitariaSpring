package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.victortavin.marmitaria.entities.UserEntity;

public class UserBalanceDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	
	Set<Add_BalanceDto> addBalance = new HashSet<>();
	
	public UserBalanceDto() {
		super();
	}

	public UserBalanceDto(Long id, String firstName, String lastName, Set<Add_BalanceDto> addBalance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addBalance = addBalance;
	}
	
	public UserBalanceDto(UserEntity userEntity) {
		super();
		this.id = userEntity.getId();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		userEntity.getAddBalance().forEach(balance -> this.addBalance.add(new Add_BalanceDto(balance)));
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

	public Set<Add_BalanceDto> getAddBalance() {
		return addBalance;
	}

	public void setAddBalance(Set<Add_BalanceDto> addBalance) {
		this.addBalance = addBalance;
	}
}




