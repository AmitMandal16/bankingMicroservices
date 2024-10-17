package com.amit.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
	
	@Autowired
	FilterUtility filterUtility;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
		
		if(isCorrelationIdPresent(httpHeaders)) {
			logger.debug("amitbank-correlation-id found in the request trace filter : {}",
					filterUtility.getCorrelationId(httpHeaders));		
		}
		else {
			String correlationID = generateCorrelationId();
			 exchange = filterUtility.setCorrelationId(exchange, correlationID);
			 logger.debug("amitbank-correlation-id found in the request trace filter : {}", correlationID);
		}
		
		return chain.filter(exchange);
	}

	private boolean isCorrelationIdPresent(HttpHeaders httpHeaders) {
		
		if(filterUtility.getCorrelationId(httpHeaders)!= null) {
			return true;
		}
		return false;
	}
	
	 private String generateCorrelationId() {
	        return java.util.UUID.randomUUID().toString();
	    }


}
