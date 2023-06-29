package com.victortavin.marmitaria.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.MessageDto;
import com.victortavin.marmitaria.service.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/emails")
public class Messagecontrollers {
	
	@Autowired
	private MessageService messageService;
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping
	public ResponseEntity<List<MessageDto>> findAllPage() {

		List<MessageDto> list = messageService.findAllPage();
		
		return ResponseEntity.ok().body(list);
	}
}
