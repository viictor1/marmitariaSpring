package com.victortavin.marmitaria.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "demands")
public class DemandsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private double price;
	private String road;
	private boolean delivered; 
}
