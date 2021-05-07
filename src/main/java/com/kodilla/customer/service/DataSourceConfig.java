package com.kodilla.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RefreshScope
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String urlDataSource;

    @Value("${spring.datasource.username}")
    private String usernameDataSource;

    @Value("${customer.datasource.password}")
    private String passwordDataSource;

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(urlDataSource);
        dataSourceBuilder.username(usernameDataSource);
        dataSourceBuilder.password(passwordDataSource);
        return dataSourceBuilder.build();
    }
}
