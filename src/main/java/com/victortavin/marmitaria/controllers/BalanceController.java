package com.victortavin.marmitaria.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.BalanceDto;
import com.victortavin.marmitaria.dtos.UserBalanceDto;
import com.victortavin.marmitaria.service.BalanceService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

@RestController
@RequestMapping(value = "/balance")
public class BalanceController {

	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@GetMapping(value = "/pentendes")
	public ResponseEntity<Page<UserBalanceDto>> findAll(Pageable pageable){
		validator.validateBank();
		
		Page<UserBalanceDto> list = balanceService.findAllPage(pageable);
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/aceitar")
	public ResponseEntity<BalanceDto> aceitarBalance(@RequestBody UserBalanceDto userBalanceDto){
		validator.validateBank();
		
		BalanceDto balanceDto = balanceService.aprovedBalance(userBalanceDto);
		
		return ResponseEntity.ok().body(balanceDto);
	}
}
