package com.myself.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class PoolConfig {


    @Bean
    public ExecutorService  executorService(){
       return new ThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000));
    }


}
