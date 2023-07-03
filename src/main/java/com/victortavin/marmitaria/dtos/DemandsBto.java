package com.victortavin.marmitaria.dtos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.victortavin.marmitaria.entities.DemandEntity;
import com.victortavin.marmitaria.entities.UserEntity;

public class DemandsBto {
	
	private Long id;
	private Double price;
	private String road;
	private int number;	
	private Boolean delivered;
	private UserDto userEntity; 
	private Set<MenuDto> menu = new HashSet<>();
	
	public DemandsBto() {
		
	}
	
	public DemandsBto(Long id, Double price, String road, int number, Boolean delivered, UserDto userEntity) {
		super();
		this.id = id;
		this.price = price;
		this.road = road;
		this.number = number;
		this.delivered = delivered;
		this.userEntity = userEntity;
	}
	
	public DemandsBto(DemandEntity d) {
		this.id = d.getId();
		this.price = d.getPrice();
		this.road = d.getRoad();
		this.number = d.getNumber();
		this.delivered = d.getDelivered();
		this.userEntity = new UserDto(d.getUserEntity());
		d.getMenu().forEach(menu -> this.menu.add(new MenuDto(menu)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public UserDto getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserDto userEntity) {
		this.userEntity = userEntity;
	}

	public Set<MenuDto> getMenu() {
		return menu;
	}

	public void setMenu(Set<MenuDto> menu) {
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
		DemandsBto other = (DemandsBto) obj;
		return Objects.equals(id, other.id);
	}

}
