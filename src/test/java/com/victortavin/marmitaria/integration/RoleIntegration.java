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
import com.victortavin.marmitaria.dtos.RoleDto;
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
		RoleDto roleDto = new RoleDto();
	    roleDto.setName("teste");

	    mockMvc.perform(MockMvcRequestBuilders.post("/roles")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(roleDto)))
	            .andExpect(MockMvcResultMatchers.status().isCreated())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("teste"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
	}
	
	@Test
	public void shouldThrowSameNameExcpetion() throws Exception {		
		RoleDto roleDto = new RoleDto();
	    roleDto.setName("User");

	    mockMvc.perform(MockMvcRequestBuilders.post("/roles")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(roleDto)))
	            .andExpect(MockMvcResultMatchers.status().is(422))
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validation exception"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Este nome já existe"));
	}
	
	
}
