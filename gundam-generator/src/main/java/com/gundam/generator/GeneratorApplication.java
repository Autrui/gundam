package com.gundam.generator;

import com.gundam.generator.config.DataConfig;
import com.gundam.util.annotation.EnableCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Autrui
 * @date 2023/8/4
 * @apiNote
 */
@EnableCustomConfig
@SpringBootApplication
public class GeneratorApplication {
    @Autowired
    private DataConfig dataConfig;

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class);
    }
}
