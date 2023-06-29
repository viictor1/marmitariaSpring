package com.victortavin.marmitaria.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.victortavin.marmitaria.entities.MessageEntity;

public class MessageDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String recipient;
	private String title;
	private String message;
	private Instant instant;
	
	public MessageDto() {
		
	}

	
	public MessageDto(Long id, String recipient, String title, String message, Instant instant) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.title = title;
		this.message = message;
		this.instant = instant;
	}



	public MessageDto(MessageEntity messageEntity) {
		this.id = messageEntity.getId();
		this.recipient = messageEntity.getRecipient();
		this.title = messageEntity.getTitle();
		this.message = messageEntity.getMessage();
		this.instant = messageEntity.getInstant();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Instant getInstant() {
		return instant;
	}


	public void setInstant(Instant instant) {
		this.instant = instant;
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
		MessageDto other = (MessageDto) obj;
		return Objects.equals(id, other.id);
	}
	
		
}
