package com.myself.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface StrategyHandle {


}
