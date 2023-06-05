package com.victortavin.marmitaria.controllers.exceptions;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victortavin.marmitaria.service.exceptions.ForbiddenException;
import com.victortavin.marmitaria.service.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExeceptionHandler {
	
	/*
	 * TokenExpiredException
	 * 
	 * */

	@ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardError> accessDenied(ForbiddenException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.FORBIDDEN.value());
        err.setError("Access Denied");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request){
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.UNAUTHORIZED.value());
		err.setError("invalid credentials");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> UsernameNotFound(UsernameNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationDto(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError validationError = new ValidationError();
		
		validationError.setTimestamp(Instant.now());
		validationError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		validationError.setError("Validation exception");
		validationError.setMessage(e.getMessage());
		validationError.setPath(request.getRequestURI());
		
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}	
	
}
