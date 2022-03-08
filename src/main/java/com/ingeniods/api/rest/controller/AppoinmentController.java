package com.ingeniods.api.rest.controller;

import static com.ingeniods.api.common.route.Route.APPOINMENT_ASING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingeniods.api.core.services.AppoinmentService;

@RestController
public class AppoinmentController {
	
	private static final String CLASS =  AppoinmentController.class.getName();
	private Logger logger = LoggerFactory.getLogger(AppoinmentController.class);
	
	private AppoinmentService appoinmentService;
	
	public AppoinmentController(AppoinmentService appoinmentService) {
		this.appoinmentService = appoinmentService;
	}
		
	@PostMapping(APPOINMENT_ASING)
	public void allCountries(){
		logger.info("{}::allCountries ",CLASS);
		appoinmentService.asingAppoinment();
	}
	
}
