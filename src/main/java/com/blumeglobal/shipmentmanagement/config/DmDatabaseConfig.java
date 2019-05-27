package com.blumeglobal.shipmentmanagement.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerDmDataSource",
    basePackages = "com.blumeglobal.shipmentmanagement.dm",
    transactionManagerRef = "transactionManagerDmDataSource")
public class DmDatabaseConfig {


   @Bean(name="entityManagerDmDataSource")
   public LocalContainerEntityManagerFactoryBean entityManagerDmDataSource(EntityManagerFactoryBuilder builder,@Qualifier("secondDataSource") DataSource dataSource) {
       return builder.dataSource(dataSource).packages("com.blumeglobal.shipmentmanagement.dm").persistenceUnit("second_DataSource").build();
    }

   @Bean(name = "transactionManagerDmDataSource")
   public PlatformTransactionManager transactionManagerDmDataSource(@Qualifier("entityManagerDmDataSource") EntityManagerFactory entityManagerFactory) {
       return new JpaTransactionManager(entityManagerFactory);
   }
}

