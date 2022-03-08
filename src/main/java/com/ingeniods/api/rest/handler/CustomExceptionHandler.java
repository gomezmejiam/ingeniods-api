package com.ingeniods.api.rest.handler;

import com.ingeniods.api.client.configuration.DefaultStatusCode;
import com.ingeniods.api.common.exception.IngenioException;
import com.ingeniods.api.common.response.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	private static final String CLASS =  CustomExceptionHandler.class.getName();
	private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(IngenioException.class)
	ResponseEntity<ErrorResponse> ingenioErrorHandler(IngenioException ex) {
		logger.error("{}::errorHandler --IngenioException [{}]",CLASS, ex.getLocalizedMessage());
		return new ResponseEntity<>(ErrorResponse.builder().status(ex.getStatus()).code(ex.getCode()).message(ex.getMessage()).build(),
				HttpStatus.valueOf(ex.getStatus()));
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorResponse> errorHandler(Exception ex) {
		logger.error("{}::errorHandler --IngenioException [{}]",CLASS, ex.getLocalizedMessage());
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		DefaultStatusCode uncontroled = DefaultStatusCode.UNCONTROLED;
		return new ResponseEntity<>(ErrorResponse.builder().status(status.value()).code(uncontroled.name()).message(uncontroled.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
