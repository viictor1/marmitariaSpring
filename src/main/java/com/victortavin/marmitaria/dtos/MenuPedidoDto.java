package com.victortavin.marmitaria.dtos;

import java.util.Objects;

import com.victortavin.marmitaria.entities.MenuEntity;

public class MenuPedidoDto {
	private String name;

	public MenuPedidoDto() {
		super();
	}

	public MenuPedidoDto(String name) {
		super();
		this.name = name;
	}
	
	public MenuPedidoDto(MenuEntity menuEntity) {
		this.name = menuEntity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuPedidoDto other = (MenuPedidoDto) obj;
		return Objects.equals(name, other.name);
	}
	
}
