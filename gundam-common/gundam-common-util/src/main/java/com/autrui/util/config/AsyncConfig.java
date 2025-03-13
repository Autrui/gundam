package com.autrui.util.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Autrui
 * @date 2024/5/27
 */
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 200;
    private static final int KEEP_ALIVE_SECONDS = 5000;
    private static final String THREAD_NAME_PREFIX = "GUNDAM-THREAD-POOL-";

    /**
     * 线程池配置
     *
     * @return java.util.concurrent.Executor
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        log.info("初始化线程池开始..................");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        // 放入主线程执行，不直接抛弃任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("初始化线程池完毕，threadPoolName={}, curePoolSize={}, maxPoolSize={}", THREAD_NAME_PREFIX, MAX_POOL_SIZE, MAX_POOL_SIZE);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return this.taskExecutor();
    }
}
