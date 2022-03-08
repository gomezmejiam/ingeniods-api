package com.ingeniods.api.client.configuration;

import feign.Logger;
import feign.Response;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.ingeniods.api.common.util.HeaderUtil;

public class FeignLogger extends Logger {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(FeignLogger.class);

	@Override
	protected void log(String configKey, String format, Object... args) {
		logger.debug(String.format(methodTag(configKey) + format, args));
	}

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
			throws IOException {

		Response responseClient = super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
		String transactionId = HeaderUtil.getTrasactionId();
		StringBuilder value = new StringBuilder();

		value.append("\n")
		.append("HTTP-REQUEST>>>>").append("\n")
		.append("--     Request: [{}]:{}").append("\n")
		.append("--    Response: {}").append("\n")
		.append("--      Status: {}").append("\n")
		.append("-- ElapsedTime: {}").append("\n")
		.append("HTTP-REQUEST<<<<<");
		logger.info(value.toString(), transactionId, responseClient.body().toString(), response.request().httpMethod(),
				response.request().url(), response.status(), elapsedTime);
		return responseClient;
	}

}
