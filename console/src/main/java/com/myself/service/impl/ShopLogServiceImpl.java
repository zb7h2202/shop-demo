package com.myself.service.impl;

import com.myself.annotation.CacheExpire;
import com.myself.console.dao.TradeLogDao;
import com.myself.console.entity.TradeLogEntity;
import com.myself.constants.ConstantsNum;
import com.myself.redis.RedisContext;
import com.myself.res.ResponseInfo;
import com.myself.service.ShopLogService;
import com.myself.spy.dao.TradeDao;
import com.myself.spy.entity.TradeEntity;
import com.myself.strategry.StrategyRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ShopLogServiceImpl implements ShopLogService {

    @Autowired
    private RedisContext redisContext;

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private TradeLogDao tradeLogDao;

    @Autowired
    private StrategyRunner strategyRunner;

//    @Autowired
//    private ESService esService;



    @Override
    public Object queryAll() {
        log.info("console query all=============");
        return tradeDao.findAll();
    }

    @Override
    @CacheExpire(value = "300")
    @Cacheable(cacheNames = "cache:trade",key = "#id")
    public Object getCache(Long id) {

        TradeLogEntity byId = tradeLogDao.findById(id).get();

        return byId;
    }

    @Override
    @CachePut(cacheNames = "cache:trade",key = "#entity.getCId()")
    public Object saveCache(TradeLogEntity entity) {
        TradeLogEntity save = tradeLogDao.save(entity);
        return save;
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

        redisContext.set("name","zhangsan",120, TimeUnit.SECONDS);


        TradeLogEntity save1 = tradeLogDao.save(tradeLogEntity);

        String name = redisContext.getStr("name");
        save1.setCName(name);

        String s1 = strategyRunner.run(ConstantsNum.s1);
        String s2 = strategyRunner.run(ConstantsNum.s2);

//        try {
//            esService.createIndex();
//            esService.bulkIndex();
//        } catch (IOException e) {
//
//        }






        log.info("s1:{},s2:{}",s1,s2);
//        int i = 1/0;

        return save1;
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
