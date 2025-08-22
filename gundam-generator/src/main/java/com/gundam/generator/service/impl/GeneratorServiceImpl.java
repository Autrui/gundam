package com.gundam.generator.service.impl;

import com.gundam.generator.config.DataConfig;
import com.gundam.generator.service.GeneratorService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
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
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPassword())
                .globalConfig(builder ->
                        builder.author("Autrui") // 设置作者
                                .outputDir(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gundam\\temp") // 输出目录
                )
                .packageConfig(builder ->
                        builder.parent("com.temp") // 设置父包名
                                .entity("entity") // 设置实体类包名
                                .mapper("mapper") // 设置 Mapper 接口包名
                                .service("service") // 设置 Service 接口包名
                                .serviceImpl("service.impl") // 设置 Service 实现类包名
                                .xml("mapper.business") // 设置 Mapper XML 文件包名
                )
                .strategyConfig(builder ->
                        builder.addInclude(schemaName) // 设置需要生成的表名
                                .entityBuilder()
                                .enableLombok(new ClassAnnotationAttributes("@Data", "lombok.Data")) // 启用 Lombok
                                .serviceBuilder()
                                .superServiceImplClass(ServiceImpl.class)
                                .mapperBuilder()
                                .mapperAnnotation(Mapper.class) // @Mapper 注解
                                .enableBaseColumnList()
                                .enableBaseResultMap() // 启用 REST 风格
                                .superClass(BaseMapper.class))
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }
}
