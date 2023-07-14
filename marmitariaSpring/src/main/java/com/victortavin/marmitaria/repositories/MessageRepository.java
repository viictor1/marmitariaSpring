package com.victortavin.marmitaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victortavin.marmitaria.entities.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
	
	List<MessageEntity> findAllByRecipient(String recipient);
}

