package com.myself.service.impl;

import com.myself.service.ShoppingService;
import com.myself.spy.dao.TradeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    @Qualifier("tradeDao")
    private TradeDao tradeDao;

    @Override
    public Object queryAll() {
        log.info("queryall ========");
        return tradeDao.findAll();
    }
}
