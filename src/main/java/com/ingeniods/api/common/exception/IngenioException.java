package com.ingeniods.api.common.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IngenioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	final boolean retryable;
	final int status;
	final String code;
	final String message;

}
