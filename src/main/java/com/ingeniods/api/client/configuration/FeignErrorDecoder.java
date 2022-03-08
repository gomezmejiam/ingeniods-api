package com.ingeniods.api.client.configuration;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeniods.api.common.exception.IngenioException;
import com.ingeniods.api.common.response.ErrorResponse;

import java.io.IOException;
import java.util.Optional;

public class FeignErrorDecoder implements ErrorDecoder {

	private static final String CLASS =  FeignErrorDecoder.class.getName();
	
	private Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

	private ObjectMapper objectMapper;
	
	public FeignErrorDecoder(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		String responseBody = readResponse(response);
		logger.error("{}::decode --bodyResponse [{}]",CLASS, responseBody);
		Optional<ErrorResponse> errorResponseOpt = Optional.empty();
		
		try {
			errorResponseOpt = Optional.of(objectMapper.readValue(responseBody, ErrorResponse.class));
			if(!errorResponseOpt.get().isValid()) {
				errorResponseOpt = Optional.empty();
			}
        }catch (JsonProcessingException ex) {}
		
		if(errorResponseOpt.isPresent()) {
			return readErrorResponse(response, errorResponseOpt.get());
		}
		
		Optional<DefaultStatusCode> defaultStatusCodeOpt = DefaultStatusCode.getByStatusName(HttpStatus.resolve(response.status()).name());
		if(defaultStatusCodeOpt.isPresent()) {
			return readDefaultStatusCode(response, defaultStatusCodeOpt);
		}
		
		return IngenioException.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.code(DefaultStatusCode.UNCONTROLED.getCode())
				.message(DefaultStatusCode.UNCONTROLED.getMessage())
				.retryable(false)
				.build();
	}

	private Exception readDefaultStatusCode(Response response, Optional<DefaultStatusCode> defaultStatusCodeOpt) {
		DefaultStatusCode defaultStatusCode = defaultStatusCodeOpt.get();
		return IngenioException.builder()
		.status(response.status())
		.code(defaultStatusCode.getCode())
		.message(defaultStatusCode.getMessage())
		.retryable(HttpStatus.resolve(response.status()).is5xxServerError())
		.build();
	}

	private IngenioException readErrorResponse(Response response, ErrorResponse errorResponse) {
		return IngenioException.builder()
				.status(errorResponse.getStatus())
				.code(errorResponse.getCode())
				.message(errorResponse.getMessage())
				.retryable(HttpStatus.resolve(response.status()).is5xxServerError())
				.build();
	}

	private String readResponse(Response response) {
		try {
			return new String(Util.toByteArray(response.body().asInputStream()), Util.UTF_8);
		} catch (IOException ex) {
			logger.error("{}:readResponse --bodyResponse [{}]", CLASS,ex.getLocalizedMessage());
		}
		return "";
	}
	
}
