package com.ingeniods.api.common.response;

import java.util.Objects;

import org.springframework.util.StringUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	Integer status;
	String code;
	String message;
	
	public boolean isValid() {
		return StringUtils.hasText(code) && StringUtils.hasText(message) && Objects.nonNull(status) && status > 0;
	}
}
