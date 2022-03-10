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
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.Propagation;

import static com.ingeniods.api.common.Constant.LOG_CORRELATION_ID;

@Component
public class TracingConfiguration  implements WebMvcConfigurer {
	
	public static final BaggageField CORRELATION_ID = BaggageField.create(LOG_CORRELATION_ID);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceInterceptor());
    }

	public @Bean Propagation.Factory propagationFactory() {
	    return BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY)
	        .add(
	        		SingleBaggageField
	        		.newBuilder(CORRELATION_ID)
	        		.addKeyName(LOG_CORRELATION_ID)
	        		.build()
	        		)
	        .build();
	  }
	
	public @Bean CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
	   return MDCScopeDecorator.newBuilder()
	         .clear()
	         .add(CorrelationScopeConfig
	            .SingleCorrelationField
	            .newBuilder(CORRELATION_ID)
	               .flushOnUpdate()
	               .build())
	         .build();
	}
    

}
