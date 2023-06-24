package com.victortavin.marmitaria.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victortavin.marmitaria.entities.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
	
	@Query("SELECT m FROM messages WHERE m.email = :email")
	Page<MessageEntity> findAllEmail(String email);
}
