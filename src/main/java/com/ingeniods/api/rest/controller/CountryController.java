package com.ingeniods.api.rest.controller;

import static com.ingeniods.api.common.route.Route.COUNTRY_BASE;
import static com.ingeniods.api.common.route.Route.COUNTRY_BY_NAME;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ingeniods.api.client.CountryClient;
import com.ingeniods.api.common.response.CountryResponse;

@RestController
public class CountryController {
	
	private static final String CLASS =  CountryController.class.getName();
	private Logger logger = LoggerFactory.getLogger(CountryController.class);
	
	private CountryClient countryService;
	
	public CountryController(CountryClient countryService) {
		this.countryService = countryService;
	}
		
	@GetMapping(COUNTRY_BASE)
	public List<CountryResponse> allCountries(){
		logger.info("{}::allCountries ",CLASS);
		return countryService.getAll();
	}
	
	@GetMapping(COUNTRY_BY_NAME)
	public List<CountryResponse> byName(@PathVariable("name") String name){
		logger.info("{}::byName --Name[{}]",CLASS,name);
		return countryService.byName(name);
	}
	
	
	
}
