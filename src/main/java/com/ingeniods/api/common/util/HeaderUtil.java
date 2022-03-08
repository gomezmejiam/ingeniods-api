package com.ingeniods.api.common.util;

import static com.ingeniods.api.common.Constant.EMPTY_STRING;
import static com.ingeniods.api.common.Constant.LOG_CORRELATION_ID;

import java.util.Optional;

import brave.baggage.BaggageField;

public class HeaderUtil {
	private HeaderUtil() {}
	
	public static String getTrasactionId() {
		return getHeaderByName(LOG_CORRELATION_ID);
	}
	
	public static String getHeaderByName(String name) {
		return Optional.ofNullable(BaggageField.getByName(name)).map(BaggageField::getValue).orElse(EMPTY_STRING);
	}

}
