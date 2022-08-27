package com.myself.threadpool;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskThread implements Runnable {

    public static ThreadLocal<TaskThread> threadLocal = new ThreadLocal<>();



    private ThreadParam threadParam;

    public TaskThread(ThreadParam threadParam) {
        this.threadParam = threadParam;
    }

    @Override
    public void run() {

        try{
            TaskThread taskThread = threadLocal.get();

            log.info("id:{},name:{},message:{}",threadParam.getId(),threadParam.getName(),threadParam.getMessage());

        }finally {
            threadLocal.remove();
        }


    }
}
