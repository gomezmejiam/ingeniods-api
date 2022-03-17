package com.ingeniods.api.rest.configuration.tracing;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ingeniods.api.rest.interceptor.TraceInterceptor;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagation;
import brave.baggage.BaggagePropagationConfig.SingleBaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.Propagation;

import static com.ingeniods.api.common.Constant.LOG_CORRELATION_ID;
import static com.ingeniods.api.common.Constant.LOG_USER_ID;

@Component
public class TracingConfiguration implements WebMvcConfigurer {

	public static final BaggageField CORRELATION_ID = BaggageField.create(LOG_CORRELATION_ID);
	public static final BaggageField USER_ID = BaggageField.create(LOG_USER_ID);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TraceInterceptor());
	}

	public @Bean Propagation.Factory propagationFactory() {
		return BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY)
				.add(createBaggageField(CORRELATION_ID))
				.add(createBaggageField(USER_ID))
				.build();
	}

	public @Bean CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
		return MDCScopeDecorator.newBuilder().clear()
				.add(createScopeConfig(CORRELATION_ID))
				.add(createScopeConfig(USER_ID))
				.build();
	}

	private SingleBaggageField createBaggageField(BaggageField baggageField) {
		return SingleBaggageField.newBuilder(baggageField).addKeyName(baggageField.name()).build();
	}

	private SingleCorrelationField createScopeConfig(BaggageField baggageField) {
		return CorrelationScopeConfig.SingleCorrelationField.newBuilder(baggageField).flushOnUpdate().build();
	}

}
