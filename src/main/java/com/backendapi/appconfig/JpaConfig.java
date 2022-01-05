package com.backendapi.appconfig;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    private final ConfigService configService;

    public JpaConfig(ConfigService configService) {
        this.configService = configService;
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url(this.configService.mainDbConfig.getUrl());
        dataSourceBuilder.username(this.configService.mainDbConfig.getUsername());
        dataSourceBuilder.password(this.configService.mainDbConfig.getPassword());
        return dataSourceBuilder.build();
    }
}
