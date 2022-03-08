package com.ingeniods.api.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ingeniods.api.client.CountryClient;
import com.ingeniods.api.common.response.CountryResponse;

@Service
public class CountryService {
	
	private static final String CLASS =  CountryService.class.getName();
	private Logger logger = LoggerFactory.getLogger(CountryService.class);
	private CountryClient countryClient;

	public CountryService(CountryClient countryClient) {
		this.countryClient = countryClient;
	}
	
	public List<CountryResponse> allCountries(){
		logger.info("{}::allCountries ",CLASS);
		return countryClient.getAll();
	}
	
	public List<CountryResponse> byName(String name){
		logger.info("{}::byName --Name[{}]",CLASS,name);
		return countryClient.byName(name);
	}
	
}
