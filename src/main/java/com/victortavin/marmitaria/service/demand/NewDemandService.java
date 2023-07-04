package com.victortavin.marmitaria.service.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.demand.DemandDto;
import com.victortavin.marmitaria.dtos.demand.DemandInsertDto;
import com.victortavin.marmitaria.dtos.menu.MenuPedidoDto;
import com.victortavin.marmitaria.entities.DemandEntity;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.DemandRepository;
import com.victortavin.marmitaria.repositories.MenuRepository;

@Service
public class NewDemandService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private DemandRepository demandRepository;
	
	public DemandDto newDemand(DemandInsertDto demandInsertDto) {
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
	
		return new DemandDto(entity);
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
