package com.autrui.service.impl;

import com.autrui.config.DataConfig;
import com.autrui.service.GeneratorService;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Autrui
 * @date 2023/8/10
 * @apiNote
 */
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Resource
    private DataConfig dataConfig;


    @Override
    public void generate(String schemaName) {
        // 数据库配置
        AutoGenerator generator = new AutoGenerator(new DataSourceConfig
                .Builder(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPassword())
                .schema(schemaName)
                .build());

        // 指定全局配置
        generator.global(new GlobalConfig.Builder()
                .build());

        // 生成策略
        generator.strategy(new StrategyConfig.Builder()
                .build());

        // 执行生成代码
        generator.execute();
    }
}
