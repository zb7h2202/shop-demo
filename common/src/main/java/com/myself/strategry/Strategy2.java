package com.myself.strategry;

import com.myself.annotation.StrategyAspectHandle;
import com.myself.annotation.StrategyFlag;
import com.myself.annotation.StrategyHandle;
import com.myself.constants.ConstantsNum;

@StrategyFlag(ConstantsNum.s2)
public class Strategy2 {


    @StrategyHandle
    public String handStrategy2(String s){
        System.out.println(s);
        return "handStrategy2";
    }

}
