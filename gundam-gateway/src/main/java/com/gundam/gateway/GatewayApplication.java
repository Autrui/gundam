package com.gundam.gateway;

import com.gundam.util.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Autrui
 * @date 2023/8/4
 * @apiNote
 */
@EnableCustomConfig
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
