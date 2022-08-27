package com.myself.consume;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQConsume {


    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")
    public void process(Map mes){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(mes));
        System.out.println("jsonObject = " + jsonObject);
    }




}




