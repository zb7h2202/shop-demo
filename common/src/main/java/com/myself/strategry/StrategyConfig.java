package com.myself.strategry;

import com.myself.annotation.StrategyFlag;
import com.myself.annotation.StrategyHandle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class StrategyConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public StrategyRunner strategyRunner() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(StrategyFlag.class);
        Map<String, Function<String, String>> strategyHandlers = new HashMap<>();

        for (Object value : beansWithAnnotation.values()) {

            String flag = value.getClass().getAnnotation(StrategyFlag.class).value();
            for (Method method : value.getClass().getMethods()) {
                if (method.isAnnotationPresent(StrategyHandle.class)) {
                    strategyHandlers.put(flag, aVoid -> {
                        Object invoke;
                        try {
                            invoke = method.invoke(value, aVoid);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return (String) invoke;
                    });
                    break;
                }
            }


        }
        return flag -> strategyHandlers.get(flag).apply(flag);

    }


}
