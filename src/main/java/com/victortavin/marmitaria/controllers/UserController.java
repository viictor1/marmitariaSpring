package com.victortavin.marmitaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.victortavin.marmitaria.service.UserService;

@RestController(value = "/users")
public class UserController {
	
	@Autowired
	public UserService service;
}
