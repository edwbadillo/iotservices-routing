package com.edwin.iot.iotspringgateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (FilterUtils.hasCorrelationId(requestHeaders)) {
            String correlationId = FilterUtils.getCorrelationId(requestHeaders);
            log.info("API GATEWAY: Correlation ID found => {}", correlationId);
        } else {
            String id = FilterUtils.generateCorrelationId();
            FilterUtils.setCorrelationId(exchange, id);
            log.info("API GATEWAY: Correlation ID generated => {}", id);
        }
        return chain.filter(exchange);
    }
    
}
