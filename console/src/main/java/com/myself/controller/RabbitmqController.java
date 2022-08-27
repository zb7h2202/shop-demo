package com.myself.controller;

import com.myself.product.RabbitMQProduct;
import com.myself.res.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitmqController {

    @Autowired
    private RabbitMQProduct rabbitMQProduct;

    @GetMapping("/rabbit/pro/{mes}")
    public ResponseInfo pro(@PathVariable String mes){
        rabbitMQProduct.pro(mes);
        return ResponseInfo.succ(null);
    }


}
