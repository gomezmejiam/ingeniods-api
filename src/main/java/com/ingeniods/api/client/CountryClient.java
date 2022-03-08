package com.ingeniods.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ingeniods.api.client.configuration.FeignConfiguration;
import com.ingeniods.api.common.response.CountryResponse;

import org.springframework.http.MediaType;

import static com.ingeniods.api.common.route.ExternalRoute.COUNTRY_ALL;
import static com.ingeniods.api.common.route.ExternalRoute.COUNTRY_BY_NAME;

import java.util.List;

@FeignClient(
name = 	"country-api",
url = "${api.client.country.url}",
configuration = FeignConfiguration.class
)
public interface CountryClient {
	
	@GetMapping(path = COUNTRY_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CountryResponse> getAll();
	
	@GetMapping(path = COUNTRY_BY_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CountryResponse> byName(@PathVariable("name") String name);

}
