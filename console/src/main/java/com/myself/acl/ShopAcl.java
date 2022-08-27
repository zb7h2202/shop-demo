package com.myself.acl;

import com.myself.console.entity.TradeLogEntity;
import com.myself.service.ShopLogService;
import com.myself.service.ShopService;
import com.myself.spy.entity.TradeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ShopAcl {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopLogService shopLogService;

    public Object save(){

        Object o = shopService.saveShop(null);
//        Object o1 = shopLogService.saveShop(null);
        return o;
    }


}
