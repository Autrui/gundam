package com.gundam.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 链路追踪过滤器
 *
 * @author Autrui
 * @date 2025/4/1
 */
@Component
@Slf4j
public class TraceFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = UUID.randomUUID().toString();
        exchange.getRequest().mutate().header("Trace-Id", traceId).build();
        log.info("[Trace ID: {}] Incoming request: {}", traceId, exchange.getRequest().getURI());
        return chain.filter(exchange).doFinally(signal -> {
            log.info("[Trace ID: {}] Response status: {}", traceId, exchange.getResponse().getStatusCode());
        });
    }

    @Override
    public int getOrder() {
        return -99;
    }
}