package com.backendapi.appconfig;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url(ConfigService.mainDbConfig.getUrl());
        dataSourceBuilder.username(ConfigService.mainDbConfig.getUsername());
        dataSourceBuilder.password(ConfigService.mainDbConfig.getPassword());
        return dataSourceBuilder.build();
    }
}
