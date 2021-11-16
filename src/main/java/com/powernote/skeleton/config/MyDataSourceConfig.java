package com.powernote.skeleton.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class MyDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "myDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource hikariDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "myTransactionManager")
    public DataSourceTransactionManager myTransactionManager(@Qualifier("myDataSource") DataSource hikariDataSource ) {
        DataSourceTransactionManager dsm = new DataSourceTransactionManager(  hikariDataSource );
        log.info("ds " + hikariDataSource.toString() );

        log.info("dsm getMaxLifetime {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getMaxLifetime() );
        log.info("dsm getConnectionTimeout {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getConnectionTimeout() );
        log.info("dsm getValidationTimeout {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getValidationTimeout() );
        log.info("dsm getPoolName {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getPoolName() );
        log.info("dsm getMaximumPoolSize {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getMaximumPoolSize() );
        log.info("dsm getIdleTimeout {}:" , ((HikariDataSource)hikariDataSource).getHikariConfigMXBean().getIdleTimeout() );
        return dsm;
    }

    @Bean(name = "myDefaultTransactionDefinition")
    public DefaultTransactionDefinition defaultTransactionDefinition(){
        return new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
    }

}