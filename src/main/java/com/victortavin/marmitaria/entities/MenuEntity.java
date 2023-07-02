package com.victortavin.marmitaria.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@Column(nullable = false)
	private float price;
	
	private float discount;
	
	@Column(nullable = false)
	private boolean active;
	
	@Column(nullable = false)
	private String description;
	
	public MenuEntity() {
		super();
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public int hashCode() {
		return Objects.hash(active, description, discount, id, name, price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuEntity other = (MenuEntity) obj;
		return active == other.active && Objects.equals(description, other.description)
				&& Float.floatToIntBits(discount) == Float.floatToIntBits(other.discount)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}	
	
 }
