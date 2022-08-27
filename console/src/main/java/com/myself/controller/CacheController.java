package com.myself.controller;

import com.myself.console.entity.TradeLogEntity;
import com.myself.res.ResponseInfo;
import com.myself.service.ShopLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CacheController {


    @Autowired
    private ShopLogService shopLogService;

    @GetMapping("/cache/get")
    public ResponseInfo getCache(@RequestParam Long id){
        Object object = shopLogService.getCache(id);
        return ResponseInfo.succ(object);
    }

    @GetMapping("/cache/save")
    public ResponseInfo putCache(){
        TradeLogEntity tradeLogEntity = new TradeLogEntity();
        tradeLogEntity.setCName("王六"+(int)(Math.random()*100));
        tradeLogEntity.setCAccount("wangwu");
        tradeLogEntity.setCIncome(new BigDecimal(10.5));
        tradeLogEntity.setCExpend(new BigDecimal(5.4));
        tradeLogEntity.setCSaving(new BigDecimal(5.1));
        Object object = shopLogService.saveCache(tradeLogEntity);
        return ResponseInfo.succ(object);
    }

}
