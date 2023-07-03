package com.victortavin.marmitaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.DemandInsertDto;
import com.victortavin.marmitaria.dtos.DemandsBto;
import com.victortavin.marmitaria.dtos.MenuPedidoDto;
import com.victortavin.marmitaria.entities.DemandEntity;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.DemandRepository;
import com.victortavin.marmitaria.repositories.MenuRepository;

@Service
public class DemandService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private DemandRepository demandRepository;
	
	public DemandsBto newDemand(DemandInsertDto demandInsertDto) {
		DemandEntity entity = new DemandEntity();
		

		
		entity.setRoad(demandInsertDto.getRoad());
		entity.setNumber(demandInsertDto.getNumber());
		entity.setDelivered(false);
		
		System.out.println(entity.getNumber());
		
		UserEntity user = recuperandoUser();
		
		entity.setUserEntity(user);
		
		entity.setPrice(0.0);
		
		for (MenuPedidoDto menuPedido : demandInsertDto.getMenu()) {
			MenuEntity menu = menuRepository.findByName(menuPedido.getName());
			
			System.out.println(menu.getPrice());
			
			entity.getMenu().add(menu);
			
			entity.setPrice(entity.getPrice() + menu.getPrice());
		}
		
		entity = demandRepository.save(entity);
	
		return new DemandsBto(entity);
	}
	
	private UserEntity recuperandoUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
		    UserEntity user = (UserEntity) authentication.getPrincipal();
		    // Faça o que precisar com o objeto User recuperado
		    return user;
		}
		return null;
	}
	

}
