package com.blumeglobal.shipmentmanagement.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MultipleDataSourceConfiguration {
    
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.bmv")
    public DataSourceProperties firstDataSourceProperties() {
    	return new DataSourceProperties();
    }

    @Bean(name="firstDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.bmv.configuration")
    public HikariDataSource firstDataSource() {
    	return firstDataSourceProperties().initializeDataSourceBuilder()
    			.type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dm")
    public DataSourceProperties secondDataSourceProperties() {
    	return new DataSourceProperties();
    }

    @Bean(name="secondDataSource")
    @ConfigurationProperties("spring.datasource.dm.configuration")
    public BasicDataSource secondDataSource() {
    	return secondDataSourceProperties().initializeDataSourceBuilder()
    			.type(BasicDataSource.class).build();
    } 
}