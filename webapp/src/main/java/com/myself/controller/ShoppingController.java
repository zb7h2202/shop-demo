package com.myself.controller;

import com.alibaba.fastjson.JSONObject;
import com.myself.res.ResponseInfo;
import com.myself.service.ShoppingService;
import com.myself.spy.dao.TradeDao;
import com.myself.spy.entity.TradeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/hello")
    public ResponseInfo testHello(){

        List<TradeEntity> all = (List<TradeEntity>) shoppingService.queryAll();
        ResponseInfo succ = ResponseInfo.succ(all);
        String s = JSONObject.toJSONString(succ);
        System.out.println("s = " + s);
        return ResponseInfo.succ(all);
    }



}
