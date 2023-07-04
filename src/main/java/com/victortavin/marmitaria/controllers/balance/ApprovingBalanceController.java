package com.victortavin.marmitaria.controllers.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.dtos.balance.BalanceDto;
import com.victortavin.marmitaria.dtos.user.UserBalanceDto;
import com.victortavin.marmitaria.service.balance.ApprovingBalanceService;
import com.victortavin.marmitaria.service.user.validation.UserAuthorityValidator;

@RestController
@RequestMapping(value = "/balance")
public class ApprovingBalanceController {
	
	@Autowired
	private ApprovingBalanceService approvingBalanceService;
	
	@Autowired
	private UserAuthorityValidator validator;
	
	@PostMapping(value = "/aceitar")
	public ResponseEntity<BalanceDto> aceitarBalance(@RequestBody UserBalanceDto userBalanceDto){
		validator.validateBank();
		
		BalanceDto balanceDto = approvingBalanceService.approvingBalance(userBalanceDto);
		
		return ResponseEntity.ok().body(balanceDto);
	}
}
