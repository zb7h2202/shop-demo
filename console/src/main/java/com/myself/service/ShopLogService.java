package com.myself.service;

import com.myself.console.entity.TradeLogEntity;
import com.myself.spy.entity.TradeEntity;

public interface ShopLogService {

    Object queryAll();

    Object getCache(Long id);

    Object saveCache(TradeLogEntity entity);

    Object saveShopAndLog(Object object);

    Object saveShop(Object object);

}
