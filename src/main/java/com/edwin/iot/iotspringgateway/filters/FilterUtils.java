package com.edwin.iot.iotspringgateway.filters;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * Defines methods for applying filters to incoming requests to the gateway 
 * service.
 */
public class FilterUtils {
    public static final String CORRELATION_ID = "Correlation-ID";
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Returns the correlation ID from headers.
     * 
     * @param headers {@link HttpHeaders}
     * @return string or null if it does not exist.
     */
    public static String getCorrelationId(HttpHeaders headers) {
        List<String> header = headers.get(CORRELATION_ID);
        if (header != null) {
            return header.stream().findFirst().get();
        }
        return null;
    }

    /**
     * Set a header to request.
     * 
     * @param exchange   {@link ServerWebExchange}
     * @param headerName header name.
     * @param value      header value.
     */
    public static void setHeader(ServerWebExchange exchange, String headerName, String value) {
        ServerHttpRequest request = exchange.getRequest().mutate().header(headerName, value).build();
        exchange = exchange.mutate().request(request).build();
    }

    /**
     * Set the corrleation ID header to request.
     * 
     * @param exchange      {@link ServerWebExchange}
     * @param correlationId the correlation ID value.
     */
    public static void setCorrelationId(ServerWebExchange exchange, String correlationId) {
        setHeader(exchange, CORRELATION_ID, correlationId);
    }

    /**
     * Generates a correlation ID.
     * 
     * @return string.
     */
    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Verifies that there is a correlation ID present in the headers of a 
     * given request.
     * 
     * @param headers {@link HttpHeaders}
     * @return true if it does exist.
     */
    public static boolean hasCorrelationId(HttpHeaders headers) {
        return getCorrelationId(headers) != null;
    }
}
