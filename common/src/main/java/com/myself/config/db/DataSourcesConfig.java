package com.myself.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourcesConfig {

    @Bean(name = "spyDataSources")
    @Qualifier(value = "spyDataSources")
    @ConfigurationProperties(prefix = "spring.datasource.druid.spy")
    @Primary
    public DataSource spyDataSources() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "consoleDataSources")
    @Qualifier(value = "consoleDataSources")
    @ConfigurationProperties(prefix = "spring.datasource.druid.console")
    public DataSource consoleDataSources() {

        return new DruidDataSource();
    }
}
