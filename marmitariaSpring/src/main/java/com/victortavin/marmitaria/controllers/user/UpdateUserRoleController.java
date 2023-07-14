package com.victortavin.marmitaria.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.victortavin.marmitaria.dtos.user.UserRoleUpdateDto;
import com.victortavin.marmitaria.service.user.UpdadeUserRoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

public class UpdateUserRoleController {
	
	@Autowired
	private UpdadeUserRoleService service;
	
	@SecurityRequirement(name = "bearerAuth")
	@Tag(name = "Update User-Role", description = "Atualizar a role de um usuário através do id da role e do usuário")
	@PutMapping(value = "updateRole")
	public ResponseEntity<String> updateUserRole(@Valid @RequestBody UserRoleUpdateDto dto){
		List<String> names = service.updateUserRole(dto.getIdUser(), dto.getRoleName());
		return ResponseEntity.ok().body(names.get(0) + " é agora um " + names.get(1));
	}
}
