package com.victortavin.marmitaria.dtos;

import java.io.Serializable;

import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;

public class Add_BalanceDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private float addValue;
	private boolean approved;
	private UserEntity userEntity;
	
	public Add_BalanceDto() {
		
	}

	public Add_BalanceDto(Long id, float addValue, boolean approved, UserEntity userEntity) {
		super();
		this.id = id;
		this.addValue = addValue;
		this.approved = approved;
		this.userEntity = userEntity;
	}
	
	public Add_BalanceDto(Add_BalanceEntity add_BalanceEntity) {
		this.id = add_BalanceEntity.getId();
		this.addValue = add_BalanceEntity.getAddValue();
		this.approved = add_BalanceEntity.isApproved();
		this.userEntity = add_BalanceEntity.getUserEntity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAddValue() {
		return addValue;
	}

	public void setAddValue(float addValue) {
		this.addValue = addValue;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
