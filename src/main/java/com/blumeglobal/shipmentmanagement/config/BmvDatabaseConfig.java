package com.blumeglobal.shipmentmanagement.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerBmvDataSource",
    basePackages = "com.blumeglobal.shipmentmanagement.dao.repositories",
    transactionManagerRef = "transactionManagerBmvDataSource")
public class BmvDatabaseConfig {


   @Primary
   @Bean(name="entityManagerBmvDataSource")
   public LocalContainerEntityManagerFactoryBean entityManagerBmvDataSource(EntityManagerFactoryBuilder builder,@Qualifier("firstDataSource") DataSource dataSource) {
       return builder.dataSource(dataSource).packages("com.blumeglobal.shipmentmanagement.model").persistenceUnit("first_DataSource").build();
    }

   @Primary
   @Bean(name = "transactionManagerBmvDataSource")
   public PlatformTransactionManager transactionManagerBmvDataSource(@Qualifier("entityManagerBmvDataSource") EntityManagerFactory entityManagerFactory) {
       return new JpaTransactionManager(entityManagerFactory);
   }
}

