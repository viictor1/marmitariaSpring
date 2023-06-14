package com.victortavin.marmitaria.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victortavin.marmitaria.dtos.Add_BalanceDto;
import com.victortavin.marmitaria.service.AddBalanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/add-balance")
public class AddBalanceController {

	@Autowired
	private AddBalanceService addBalanceService;
	
	@PostMapping
	public ResponseEntity<Add_BalanceDto> addBalance(@Valid @RequestBody float addValue){
		Add_BalanceDto addBalanceDto = addBalanceService.newBalance(addValue);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id")
				.buildAndExpand(addBalanceDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(addBalanceDto);
	}

}
