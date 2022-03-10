package com.ingeniods.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ingeniods.api.client.configuration.FeignConfiguration;

@FeignClient(
name = 	"appoinment-api",
url = "${api.client.appoinment.url}",
configuration = FeignConfiguration.class
)
public interface AppoinmentClient {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void asingAppoinment(@RequestHeader("test") String test);
	
	

}
