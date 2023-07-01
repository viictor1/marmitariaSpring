package com.victortavin.marmitaria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victortavin.marmitaria.entities.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
	MenuEntity findByName(String name);
	Optional<MenuEntity> findById(Long id);
	
	@Query(value = "SELECT m FROM MenuEntity m WHERE m.active = TRUE")
	List<MenuEntity> findAllActiveMenu();
}
