package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.victortavin.marmitaria.service.validation.menu.MenuInsertValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@MenuInsertValid
public class MenuInsertDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo name é obrigatório")
	private String name;
	
	@Positive(message = "Preço deve ser maior que 0")
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
		MenuInsertDto other = (MenuInsertDto) obj;
		return Objects.equals(description, other.description)
				&& Float.floatToIntBits(discount) == Float.floatToIntBits(other.discount)
				&& Objects.equals(name, other.name) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}

	public MenuInsertDto() {
		super();
	}
	
	
}
