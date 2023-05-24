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
import com.victortavin.marmitaria.controllers.RoleController;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.service.RoleService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoleIntegration {

	@Mock
	private RoleService service;
	
	@InjectMocks
	private RoleController roleController;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objMap = new ObjectMapper();
	
	@Test
	public void shouldAddRole() throws Exception {		
		RoleEntity role = new RoleEntity();
		role.setName("teste");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/roles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(role)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Usuário cadastrado com sucesso!"));
		
	}
	
	
}
