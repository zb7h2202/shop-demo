package com.myself.service.impl;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.myself.console.dao.TradeLogDao;
import com.myself.console.entity.TradeLogEntity;
import com.myself.service.ShopLogService;
import com.myself.service.ShopService;
import com.myself.spy.dao.TradeDao;
import com.myself.spy.entity.TradeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ShopServiceImpl implements ShopService {

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private TradeLogDao tradeLogDao;

    @Autowired
    private ShopLogService shopLogService;


    @Override
    public Object queryAll() {
        log.info("console query all=============");
        return tradeDao.findAll();
    }

    @Override
    public Object saveShopAndLog(Object object) {

        List<TradeEntity> all = tradeDao.findAll();
        List<TradeLogEntity> collect = all.stream().map(e -> convert(e)).collect(Collectors.toList());
        List<TradeLogEntity> tradeLogEntities = tradeLogDao.saveAll(collect);

        return tradeLogEntities;
    }

    @Override
    public Object saveShop(Object object) {
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setCName("王五"+(int)(Math.random()*100));
        tradeEntity.setCAccount("wangwu");
        tradeEntity.setCIncome(new BigDecimal(10.5));
        tradeEntity.setCExpend(new BigDecimal(5.4));
        tradeEntity.setCSaving(new BigDecimal(5.1));
        TradeLogEntity tradeLogEntity = convert(tradeEntity);

        TradeEntity save = tradeDao.save(tradeEntity);

        Object o = shopLogService.saveShop(tradeLogEntity);

//        int i = 1/0;

        return o;
    }


    public TradeLogEntity convert(TradeEntity e){
        TradeLogEntity logEntity = new TradeLogEntity();
        logEntity.setCId(e.getCId());
        logEntity.setCAccount(e.getCAccount());
        logEntity.setCExpend(e.getCExpend());
        logEntity.setCIncome(e.getCIncome());
        logEntity.setCName(e.getCName());
        logEntity.setCSaving(e.getCSaving());
        return logEntity;
    }

}
