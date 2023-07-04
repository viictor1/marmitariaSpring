package com.victortavin.marmitaria.dtos.demand;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.victortavin.marmitaria.dtos.menu.MenuPedidoDto;
import com.victortavin.marmitaria.entities.DemandEntity;

public class DemandInsertDto {

	private Long id;
	private String road;
	private int number;
	
	private Set<MenuPedidoDto> menu = new HashSet<>();
	
	public DemandInsertDto () {
		
	}

	public DemandInsertDto(Long id, String road, int number) {
		super();
		this.id = id;
		this.road = road;
		this.number = number;
	}
	
	public DemandInsertDto(DemandEntity d) {
		this.id = d.getId();
		this.road = d.getRoad();
		this.number = d.getNumber();
		d.getMenu().forEach(menu -> this.menu.add(new MenuPedidoDto()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Set<MenuPedidoDto> getMenu() {
		return menu;
	}

	public void setMenu(Set<MenuPedidoDto> menu) {
		this.menu = menu;
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
		DemandInsertDto other = (DemandInsertDto) obj;
		return Objects.equals(id, other.id);
	}
	
}
