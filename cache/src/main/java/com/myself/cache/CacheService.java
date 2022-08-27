package com.myself.cache;


import com.myself.spy.entity.TradeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class CacheService {



    @Cacheable(cacheNames = "trade",key = "#id")
    public TradeEntity get(Long id){

        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setCSaving(new BigDecimal(10.11));
        tradeEntity.setCId(1111l);
        tradeEntity.setCName("cache");

        return tradeEntity;

    }



}
