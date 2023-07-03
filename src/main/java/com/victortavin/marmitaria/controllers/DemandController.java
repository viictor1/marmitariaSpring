package com.victortavin.marmitaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.DemandInsertDto;
import com.victortavin.marmitaria.dtos.DemandsBto;
import com.victortavin.marmitaria.service.DemandService;



@RestController
@RequestMapping(value = "/demand")
public class DemandController {
	
	@Autowired
	private DemandService service;
	
	@PostMapping
	public ResponseEntity<DemandsBto> newDemand(@RequestBody DemandInsertDto demandInsertDto){
		DemandsBto demand = service.newDemand(demandInsertDto);
		
		return ResponseEntity.ok().body(demand);
		
	}
} 
