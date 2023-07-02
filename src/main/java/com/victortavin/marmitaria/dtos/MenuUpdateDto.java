package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

public class MenuUpdateDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;

	private float price;
	
	private float discount;
	
	private String description;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public MenuUpdateDto() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, discount, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuUpdateDto other = (MenuUpdateDto) obj;
		return Objects.equals(description, other.description)
				&& Float.floatToIntBits(discount) == Float.floatToIntBits(other.discount)
				&& Objects.equals(name, other.name) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}
	
	
}
