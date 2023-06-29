package com.victortavin.marmitaria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victortavin.marmitaria.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(String name);
}
