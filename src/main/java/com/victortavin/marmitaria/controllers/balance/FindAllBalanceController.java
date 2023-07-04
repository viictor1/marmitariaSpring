package com.victortavin.marmitaria.controllers.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.user.UserBalanceDto;
import com.victortavin.marmitaria.service.balance.FindAllPageBalanceService;
import com.victortavin.marmitaria.service.validation.user.UserAuthorityValidator;

@RestController
@RequestMapping(value = "/balance")
public class FindAllBalanceController {
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@Autowired
	private FindAllPageBalanceService findAllPageBalanceService;
	
	@GetMapping(value = "/pentendes")
	public ResponseEntity<Page<UserBalanceDto>> findAll(Pageable pageable){
		validator.validateBank();
		
		Page<UserBalanceDto> list = findAllPageBalanceService.findAllPage(pageable);
		
		return ResponseEntity.ok().body(list);
	}
}
