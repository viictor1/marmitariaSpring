package com.victortavin.marmitaria.service.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.victortavin.marmitaria.dtos.demand.DemandDto;
import com.victortavin.marmitaria.dtos.demand.DemandInsertDto;
import com.victortavin.marmitaria.dtos.menu.MenuPedidoDto;
import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.DemandEntity;
import com.victortavin.marmitaria.entities.MenuEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.AddBalanceRepository;
import com.victortavin.marmitaria.repositories.DemandRepository;
import com.victortavin.marmitaria.repositories.MenuRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.InsufficientBalanceException;

@Service
public class NewDemandService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private DemandRepository demandRepository;
	
	@Autowired
	private AddBalanceRepository addBalancerepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public DemandDto newDemand(DemandInsertDto demandInsertDto) {
		DemandEntity entity = new DemandEntity();
		
		
		
		entity.setRoad(demandInsertDto.getRoad());
		entity.setNumber(demandInsertDto.getNumber());
		entity.setDelivered(false);
		
		UserEntity user = recuperandoUser();
		
		entity.setUserEntity(user);
		
		entity.setPrice(0.0);
		
		Add_BalanceEntity addBalanceEntity = addBalancerepository.findLatestAddBalanceByUser(user);
		
		System.out.println(addBalanceEntity.getId()+ " teste " +addBalanceEntity.getAddValue());
		
		if(addBalanceEntity.getAddValue() >= 120) {
			for (MenuPedidoDto menuPedido : demandInsertDto.getMenu()) {
				MenuEntity menu = menuRepository.findByName(menuPedido.getName());
				
				System.out.println(menu.getPrice());
				
				entity.getMenu().add(menu);
				
				entity.setPrice(entity.getPrice() + (menu.getPrice()- menu.getDiscount()));
			}
			
		}else {
			for (MenuPedidoDto menuPedido : demandInsertDto.getMenu()) {
				MenuEntity menu = menuRepository.findByName(menuPedido.getName());
				
				System.out.println(menu.getPrice());
				
				entity.getMenu().add(menu);
				
				entity.setPrice(entity.getPrice() + menu.getPrice());
			}
		}
		
		
		
		if(user.getBalance().getValue()<= entity.getPrice()) {
			throw new InsufficientBalanceException("Saldo insulficiente");
		}
		
		user.getBalance().setValue(user.getBalance().getValue() -  entity.getPrice());;
		
		user = userRepository.save(user);
		
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
