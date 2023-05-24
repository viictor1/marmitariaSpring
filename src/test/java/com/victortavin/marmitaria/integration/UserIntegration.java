package com.victortavin.marmitaria.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victortavin.marmitaria.controllers.UserController;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegration {

	@Mock
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objMap = new ObjectMapper();
	
	@Test
	public void shouldAddUser() throws  Exception {
		UserInsertDto dto = new UserInsertDto();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setCpf("10.0321-92");
		dto.setEmail("user@test.com");
		dto.setPassword("123");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(dto)))
	            .andExpect(MockMvcResultMatchers.status().isCreated())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Test"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("User"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("10.0321-92"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user@test.com"));
	}
}
