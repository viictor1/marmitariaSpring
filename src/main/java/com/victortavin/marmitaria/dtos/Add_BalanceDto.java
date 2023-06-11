package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

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
	}
	
	public Add_BalanceDto(Add_BalanceEntity add_BalanceEntity) {
		this.id = add_BalanceEntity.getId();
		this.addValue = add_BalanceEntity.getAddValue();
		this.approved = add_BalanceEntity.isApproved();
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
		Add_BalanceDto other = (Add_BalanceDto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
