package com.victortavin.marmitaria.controllers.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.demand.DemandDto;
import com.victortavin.marmitaria.dtos.demand.DemandInsertDto;
import com.victortavin.marmitaria.service.demand.NewDemandService;



@RestController
@RequestMapping(value = "/demand")
public class NewDemandController {
	
	@Autowired
	private NewDemandService service;
	
	@PostMapping
	public ResponseEntity<DemandDto> newDemand(@RequestBody DemandInsertDto demandInsertDto){
		DemandDto demand = service.newDemand(demandInsertDto);
		
		return ResponseEntity.ok().body(demand);
		
	}
} 
