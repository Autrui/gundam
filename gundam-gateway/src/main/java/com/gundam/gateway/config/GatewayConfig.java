package com.gundam.gateway.config;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.ObjectMapper;
import com.gundam.gateway.filter.JwtAuthFilter;
import com.gundam.gateway.filter.TraceFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

/**
 * 网关配置类
 *
 * @author Autrui
 * @date 2025/4/1
 */
@Configuration
@RefreshScope
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GatewayConfig {
    @Value("${gateway.routes}")
    private String routesConfig;

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routesBuilder = builder.routes();
        // 从 Nacos 配置获取 JSON 格式的路由配置
        List<RouteDefinition> routeDefinitions = this.parseRoutesConfig(routesConfig);
        for (RouteDefinition route : routeDefinitions) {
            routesBuilder.route(route.getId(), r -> r.path(route.getPath())
                    .filters(f -> f.filter(jwtAuthFilter).filter(new TraceFilter()))
                    .uri(route.getUri()));
        }
        return routesBuilder.build();
    }

    private List<RouteDefinition> parseRoutesConfig(String config) {
        // 解析 JSON 配置
        try {
            ObjectMapper objectMapper = new ObjectMapper(config);
            return objectMapper.readValue(config, new TypeReference<List<RouteDefinition>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse routes configuration", e);
        }
    }

    public static class RouteDefinition {
        private String id;
        private String path;
        private String uri;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
