package com.ingeniods.api.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfiguration {
	
	@Bean 
	public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
		return new FeignErrorDecoder(objectMapper);
	}
	
	@Bean 
	public Logger feignLogger() {
		return new FeignLogger();
	}
	

}
