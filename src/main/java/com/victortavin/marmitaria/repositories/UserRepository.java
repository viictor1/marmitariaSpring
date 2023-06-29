package com.victortavin.marmitaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	UserEntity findByCpf(String cpf);
	List<UserEntity> findAllByRole(RoleEntity role);
}
