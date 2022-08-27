package com.myself.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.myself.spy",
        entityManagerFactoryRef = "spyComponentEntityManager",
        transactionManagerRef = "spyComponentTransactionManager")
public class SpyJpaDataSourcesConfig {


    @Autowired
    @Qualifier("spyDataSources")
    private DataSource spyDataSources;


    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }


    @Bean("spyComponentEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(spyDataSources)
                .packages("com.myself.spy") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
        return entityManagerFactory;
    }

//    @Bean("spyComponentTransactionManager")
//    @Primary
//    public PlatformTransactionManager buildTransactionManager(
//            @Qualifier("spyComponentEntityManager") LocalContainerEntityManagerFactoryBean buildEntityManager) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(buildEntityManager.getObject());
//        return transactionManager;
//    }

    @Primary
    @Bean(name = "spyComponentTransactionManager")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
