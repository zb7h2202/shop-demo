package com.myself.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ThreadPoolService {


    @Autowired
    private ExecutorService executorService;


    public void executor(TaskThread taskThread){
        executorService.execute(taskThread);
    }







}
