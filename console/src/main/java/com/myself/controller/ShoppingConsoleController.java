package com.myself.controller;


import com.myself.acl.ShopAcl;
import com.myself.console.entity.TradeLogEntity;
import com.myself.res.ResponseInfo;
import com.myself.service.ShopService;
import com.myself.spy.entity.TradeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ShoppingConsoleController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopAcl shopAcl;

    @GetMapping("/hello")
    public ResponseInfo test() {
        List<TradeEntity> all = (List<TradeEntity>) shopService.queryAll();
        log.info("all:{}",all);
        return ResponseInfo.succ(all);

    }

    @GetMapping("/saveLog")
    public ResponseInfo saveLog() {
        List<TradeLogEntity> all = (List<TradeLogEntity>) shopService.saveShopAndLog(null);
        log.info("all:{}",all);
        return ResponseInfo.succ(all);

    }

    @GetMapping("/save")
    public ResponseInfo save() {
        Object save = shopAcl.save();
        return ResponseInfo.succ(save);

    }
}
