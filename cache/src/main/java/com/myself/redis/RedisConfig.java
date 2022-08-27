package com.myself.redis;

import com.myself.annotation.CacheExpire;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableCaching
public class RedisConfig implements ApplicationContextAware {

    private static final ConcurrentHashMap<String,String> cacheNames = new ConcurrentHashMap();


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //默认过期时间2天
        RedisCacheConfiguration defaultConfiguration = getRedisCacheConfigurationWithTtl(2 * 24 * 3600);
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .builder(connectionFactory)
                .cacheDefaults(defaultConfiguration);
        if (!CollectionUtils.isEmpty(cacheNames)) {
            Map<String, RedisCacheConfiguration> cacheConfigurations = new LinkedHashMap<>();
            for (String cacheName : cacheNames.keySet()) {
                long ttl = Long.parseLong(cacheNames.get(cacheName));
                RedisCacheConfiguration customizedConfiguration = getRedisCacheConfigurationWithTtl(ttl);
                cacheConfigurations.put(cacheName, customizedConfiguration);
            }
            if (cacheConfigurations.size() > 0) {
                builder.withInitialCacheConfigurations(cacheConfigurations);
            }
        }
        return builder.build();

    }


    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(long ttl) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(ttl));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!(applicationContext instanceof ConfigurableApplicationContext)) {
            return;
        }
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        //CacheOperationSource 可以被提前实例化，是spring cache提供的工具类
        CacheOperationSource cacheOperationSource = context.getBean(CacheOperationSource.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> beanClass = beanDefinition.resolveBeanClass(ClassUtils.getDefaultClassLoader());
                if (beanClass != null) {
                    ReflectionUtils.doWithMethods(beanClass, m -> {
                                Collection<CacheOperation> cacheOperations = cacheOperationSource.getCacheOperations(m, beanClass);
                                if (!CollectionUtils.isEmpty(cacheOperations)) {
                                    for (CacheOperation operation : cacheOperations) {
                                        Method[] methods = beanClass.getMethods();
                                        for (Method method : methods) {
                                            CacheExpire annotation = method.getAnnotation(CacheExpire.class);
                                            if(annotation != null){
                                                Set<String> cacheNames = operation.getCacheNames();
                                                for (String cacheName : cacheNames) {
                                                    RedisConfig.cacheNames.put(cacheName,annotation.value());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                    );
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
