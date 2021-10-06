package com.nugroho.spring.api.kernel.configs;

import java.util.concurrent.Executor;

import com.nugroho.spring.api.global.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(Config.BEAN_THREAD_EXECUTOR)
    public Executor threadPoolTaskExecutor() {

        int corePoolSize = 8;
        int maxPoolSize = 10;
        int queueCapacity = 100;

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();

        return executor;
    }

}
