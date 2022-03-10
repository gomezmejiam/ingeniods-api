package com.ingeniods.api.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ingeniods.api.client.AppoinmentClient;

@Service
public class AppoinmentService {
	
	private static final String CLASS =  AppoinmentService.class.getName();
	private Logger logger = LoggerFactory.getLogger(AppoinmentService.class);
	private AppoinmentClient appoinmentClient;

	public AppoinmentService(AppoinmentClient appoinmentClient) {
		this.appoinmentClient = appoinmentClient;
	}
	
	public void asingAppoinment(){
		logger.info("{}::asingAppoinment ",CLASS);
		appoinmentClient.asingAppoinment("ertyuil");
	}
	
}
