package com.victortavin.marmitaria.dtos;

import java.io.Serializable;

import com.victortavin.marmitaria.entities.Add_Balance;

public class Add_BalanceDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private float addValue;
	private boolean approved;
	
	public Add_BalanceDto() {
		
	}

	public Add_BalanceDto(Long id, float addValue, boolean approved) {
		super();
		this.id = id;
		this.addValue = addValue;
		this.approved = approved;
	}
	
	public Add_BalanceDto(Add_Balance add_Balance) {
		this.id = add_Balance.getId();
		this.addValue = add_Balance.getAddValue();
		this.approved = add_Balance.isApproved();
	}
}
