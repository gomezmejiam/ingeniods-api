package com.ingeniods.api.rest.interceptor;

import static com.ingeniods.api.common.Constant.LOG_CORRELATION_ID;
import static com.ingeniods.api.common.Constant.EMPTY_STRING;
import static com.ingeniods.api.rest.configuration.TracingConfiguration.CORRELATION_ID;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import brave.baggage.BaggageField;


@Component
public class TraceInterceptor implements HandlerInterceptor {
	

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String transactionId = Optional.ofNullable(BaggageField.getByName(LOG_CORRELATION_ID)).map(BaggageField::getValue).orElse(EMPTY_STRING);
        if (!StringUtils.hasText(transactionId)) {
        	CORRELATION_ID.updateValue(UUID.randomUUID().toString());
        }
        return true;
    }
   
}
