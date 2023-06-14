package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.service.validation.menu.MenuInsertValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@MenuInsertValid
public class MenuDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo name é obrigatório")
	private String name;
	
	@Positive(message = "Preço deve ser maior que 0")
	private float price;
	
	private float discount;

	@Override
	public int hashCode() {
		return Objects.hash(discount, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDto other = (MenuDto) obj;
		return Float.floatToIntBits(discount) == Float.floatToIntBits(other.discount) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}

	public MenuDto() {
		super();
	}
	
	public MenuDto(MenuEntity menuEntity) {
		id = menuEntity.getId();
		name = menuEntity.getName();
		price = menuEntity.getPrice();
		discount = menuEntity.getDiscount();
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

}
