package com.victortavin.marmitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victortavin.marmitaria.entities.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
	MenuEntity findByName(String name);
}
