package com.myself.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {

    @Pointcut("execution(* com.myself.strategry.StrategyRunner.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around===============");
        proceedingJoinPoint.proceed();
    }

    @Before("pointcut()")
    public void before(){
        System.out.println("before======");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after(); = ==========" );
    }

}
