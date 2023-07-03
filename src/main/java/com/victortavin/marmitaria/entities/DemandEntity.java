package com.victortavin.marmitaria.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Demands")
public class DemandEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double price;
	private String road;
	private int number;	
	private Boolean delivered;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private UserEntity userEntity; 
	
	@ManyToMany(fetch = FetchType.EAGER)//para já trazer os roles
	@JoinTable(
			name = "tb_demand_menu",
			joinColumns = @JoinColumn(name = "menu_id"),
			inverseJoinColumns = @JoinColumn(name = "demand_id")
			)
	private Set<MenuEntity> menu = new HashSet<>();
	
	public DemandEntity () {
		
	}

	public DemandEntity(Long id, Double price, String road, int number, Boolean delivered, UserEntity userEntity) {
		super();
		this.id = id;
		this.price = price;
		this.road = road;
		this.number = number;
		this.delivered = delivered;
		this.userEntity = userEntity;
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Set<MenuEntity> getMenu() {
		return menu;
	}

	public void setMenu(Set<MenuEntity> menu) {
		this.menu = menu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(menu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandEntity other = (DemandEntity) obj;
		return Objects.equals(menu, other.menu);
	}
	
	
}
