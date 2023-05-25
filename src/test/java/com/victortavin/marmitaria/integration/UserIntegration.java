package com.victortavin.marmitaria.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victortavin.marmitaria.dtos.UserInsertDto;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegration {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objMap = new ObjectMapper();
	
	@Test
	public void shouldAddUser() throws  Exception {
		UserInsertDto dto = new UserInsertDto();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setCpf("111.111.111-00");
		dto.setEmail("user@user.com");
		dto.setPassword("123");		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(dto)))
	            .andExpect(MockMvcResultMatchers.status().isCreated())
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Test"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("User"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("111.111.111-00"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user@user.com"));
	}
	
	@Test
	public void shouldThrowCpfAlreadyExists() throws  Exception {
		UserInsertDto dto = new UserInsertDto();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setCpf("111.111.111-11");
		dto.setEmail("user@user.com");
		dto.setPassword("123");		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(dto)))
	            .andExpect(MockMvcResultMatchers.status().is(422))
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validation exception"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Este cpf já existe"));
	}
	
	@Test
	public void shouldThrowEmailAlreadyExists() throws  Exception {
		UserInsertDto dto = new UserInsertDto();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setCpf("104.032.231-99");
		dto.setEmail("user@test.com");
		dto.setPassword("123");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objMap.writeValueAsString(dto)))
	            .andExpect(MockMvcResultMatchers.status().is(422))
	            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validation exception"))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Este email já existe"));
	}
	
	@Test
	public void createdUserShouldHaveUserRole() {
		UserInsertDto dto = new UserInsertDto();
		dto.setFirstName("Test");
		dto.setLastName("User");
		dto.setCpf("104.032.231-92");
		dto.setEmail("new@user.com");
		dto.setPassword("123");
		
		service.addUser(dto);
		
		UserEntity user = userRepository.findByEmail("new@user.com");
		RoleEntity role = roleRepository.findByName("User");
		
		assertThat(user.getRole().equals(role));
	}
}
