package com.myself.strategry;

import com.myself.annotation.StrategyAspectHandle;
import com.myself.annotation.StrategyFlag;
import com.myself.annotation.StrategyHandle;
import com.myself.constants.ConstantsNum;
import com.myself.enums.StrategyEnum;
import com.myself.product.RabbitMQProduct;
import org.springframework.beans.factory.annotation.Autowired;

@StrategyFlag(ConstantsNum.s1)
public class Strategy1 {

    @Autowired
    private RabbitMQProduct product;

    @StrategyHandle
    public String handStrategy1(String s){
        product.pro(s);
        System.out.println(s);
        return "handStrategy1";
    }


}
