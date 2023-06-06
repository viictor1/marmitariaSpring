package com.victortavin.marmitaria.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "add_balance")
public class Add_Balance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float addValue;
	private boolean approved;
	
	public Add_Balance() {
		
	}
	
	public Add_Balance(Long id, float addValue, boolean approved) {
		super();
		this.id = id;
		this.addValue = addValue;
		this.approved = approved;
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
		Add_Balance other = (Add_Balance) obj;
		return Objects.equals(id, other.id);
	}
}
