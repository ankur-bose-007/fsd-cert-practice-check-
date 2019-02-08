package com.practice.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.ErrorResponse;

public class GlobalErrorHandlerController {
	Logger logger=LoggerFactory.getLogger(GlobalErrorHandlerController.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception ex){
		logger.info("",ex);
		logger.info("In error handler");
		ErrorResponse error = new ErrorResponse();
		String errorMessage="";
		error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		if(ex instanceof MethodArgumentNotValidException){
			Iterator<FieldError> iterator=((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().stream().iterator();
			while(iterator.hasNext()){
				errorMessage+=iterator.next().getDefaultMessage();
			}
			error.setReasonCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(errorMessage);
		}
		if(ex instanceof BadCredentialsException){
			logger.info("bad credentials");
			error.setErrorMessage("Wrong credentials");
		}
		if(ex instanceof AuthenticationException){
			logger.info("authentication exception");
			error.setErrorMessage("Wrong credentials");
		}
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	} 
}
