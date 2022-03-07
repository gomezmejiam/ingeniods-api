package com.ingeniods.api.rest.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ingeniods.api.common.route.Route.HEALTH;

@RestController
public class HealthController {

	private Map<String,Object> status = new HashMap<>();
	
	@GetMapping(HEALTH)
	public Map<String,Object> status(){
		status.put("status", "UP");
		status.put("time", LocalDateTime.now().toString());
		return status;
	}
	
}
