package com.autrui.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Autrui
 * @date 2024/5/27
 */
// 开启线程异步执行
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    private final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);
    private static final int CORE_POOL_SIZE = 30;
    private static final int MAX_POOL_SIZE = 30;
    private static final int QUEUE_CAPACITY = 20;
    private static final String THREAD_NAME_PREFIX = "GUNDAM-THREAD-POOL-";

    /**
     * 线程池配置
     *
     * @return java.util.concurrent.Executor
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        logger.info("初始化线程池开始..................");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        logger.info("初始化线程池完毕，threadPoolName={}, curePoolSize={}, maxPoolSize={}", THREAD_NAME_PREFIX, MAX_POOL_SIZE, MAX_POOL_SIZE);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return this.taskExecutor();
    }
}
