package com.example.logs.forwarding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogForwardingExecutorConfig {

    public static final String LOG_FORWARDING_EXECUTOR = "logForwardingExecutor";

    @Bean(name = LOG_FORWARDING_EXECUTOR)
    ExecutorService logForwardingExecutor() {
        return Executors.newFixedThreadPool(
                100
        );
    }
}
