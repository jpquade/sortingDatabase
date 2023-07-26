package com.sorting.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configures threads for use in the program by adjusting
 * thread count and the max threads that will be used.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * Task executor executor.
     *
     * @return the thread configuration for use in
     * any processes that use threads.
     */
    @Bean
    public Executor taskExecutor(){

        // Set threading properties
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Thread - ");
        executor.initialize();
        return executor;
    }
}
