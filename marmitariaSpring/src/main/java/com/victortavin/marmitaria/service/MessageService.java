package com.victortavin.marmitaria.service;



import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victortavin.marmitaria.dtos.message.MessageDto;
import com.victortavin.marmitaria.entities.MessageEntity;
import com.victortavin.marmitaria.entities.RoleEntity;
import com.victortavin.marmitaria.entities.UserEntity;
import com.victortavin.marmitaria.repositories.MessageRepository;
import com.victortavin.marmitaria.repositories.RoleRepository;
import com.victortavin.marmitaria.repositories.UserRepository;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public List<MessageDto> findAllPage(){
		UserEntity userEntity = recuperandoEmail();
		String email = userEntity.getEmail();
			
		List<MessageEntity> list = repository.findAllByRecipient(email);
		
		Collections.reverse(list);
		
		return list.stream().map(MessageDto::new).collect(Collectors.toList());
	}
	
	public void userRegisteredSuccessfully(String email, String name) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setRecipient(email);
		messageEntity.setTitle("Cadastro feito com sulcesso");
		messageEntity.setMessage("Ola "+name+", seja bem vindo ao marmitaria Spring!");
		messageEntity.setInstant(Instant.now());
		
		repository.save(messageEntity);
	}
	
	public void userLogin (String email, String dispositivo) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setRecipient(email);
		messageEntity.setTitle("Login feito em um dispositivo");
		messageEntity.setMessage("Um dispositvo ("+dispositivo+") fez login com o seu usuário, é você mesmo?");
		messageEntity.setInstant(Instant.now());
		
		repository.save(messageEntity);
	}
	
	public void addSaldoUser(float saldo) {
		UserEntity userEntity = recuperandoEmail();
		String email = userEntity.getEmail();
		
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setRecipient(email);
		messageEntity.setTitle("Saldo adicionado com sulcesso");
		messageEntity.setMessage("Olá, você acabou adicionar um saldo de "+saldo+
				" , agora só esperar a confirmação do banco para o saldo ser adicioando à carteira.");
		messageEntity.setInstant(Instant.now());
		
		repository.save(messageEntity);
	}
	
	public void addSaldoBanco(float saldo) {
		UserEntity userEntity = recuperandoEmail();
		
		Optional<RoleEntity> roleOptional = roleRepository.findByName("Bank");
		
		RoleEntity role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Role não encontrada"));
		
		List<UserEntity> list = userRepository.findAllByRole(role);
		
		for (UserEntity userDto : list) {
			if (userDto.getRole().getName().equals("Bank")) {
				MessageEntity messageEntity = new MessageEntity();
				
				messageEntity.setRecipient(userDto.getEmail());
				messageEntity.setTitle("Solicitação de saldo");
				messageEntity.setMessage("Olá " + userDto.getFirstName() + ". o " 
						+ userEntity.getFirstName() + " adicionou um novo saldo de " 
						+ saldo + "e está esperando a aceitação do banco.");
				messageEntity.setInstant(Instant.now());
				
				repository.save(messageEntity);
			}
		}
		
	}
	
	public void saldoAdicionado (String email, float value, double saldo) {
		MessageEntity entity = new MessageEntity();
		
		entity.setRecipient(email);
		entity.setTitle("Saldo Aprovado!");
		entity.setMessage("O seu saldo de " + value + "foi aprovado. Agora você tem " +
		saldo + " de saldo na carteira.");
		entity.setInstant(Instant.now());
		
		repository.save(entity);
	}
	
	public String informacoesDoDispositivo(HttpServletRequest request) {
        String userAgentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        
        String navegador = userAgent.getBrowser().getName();
        String sistemaOperacional = userAgent.getOperatingSystem().getName();
        
        return "navagador: " + navegador + " sitema operacional: " + sistemaOperacional;
	}
	
	private UserEntity recuperandoEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
		    UserEntity user = (UserEntity) authentication.getPrincipal();
		    // Faça o que precisar com o objeto User recuperado
		    return user;
		}
		return null;
	}
}
