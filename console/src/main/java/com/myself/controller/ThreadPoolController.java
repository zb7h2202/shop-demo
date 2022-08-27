package com.myself.controller;


import com.myself.res.ResponseInfo;
import com.myself.threadpool.TaskThread;
import com.myself.threadpool.ThreadParam;
import com.myself.threadpool.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadPoolController {


    @Autowired
    ThreadPoolService threadPoolService;


    @GetMapping("/pool/thread/add")
    public ResponseInfo addThread(){

        for (int i = 0; i < 1000; i++) {
            ThreadParam threadParam = new ThreadParam(Long.valueOf(i), "name-"+i, "message-"+i);
            threadPoolService.executor(new TaskThread(threadParam));
        }



        return ResponseInfo.succ(null);

    }


}
