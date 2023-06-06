package com.victortavin.marmitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victortavin.marmitaria.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	UserEntity findByCpf(String cpf);
}
