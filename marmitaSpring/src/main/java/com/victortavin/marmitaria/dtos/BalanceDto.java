package com.victortavin.marmitaria.dtos;

import java.io.Serializable;

import com.victortavin.marmitaria.entities.BalanceEntity;

public class BalanceDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private	 double balance;
	
	public BalanceDto() {
		
	}

	public BalanceDto(Long id, float balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	
	public BalanceDto(BalanceEntity balance) {
		super();
		this.id = balance.getId();
		this.balance = balance.getValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValue() {
		return balance;
	}

	public void setValue(double balance) {
		this.balance = balance;
	}

	
}
