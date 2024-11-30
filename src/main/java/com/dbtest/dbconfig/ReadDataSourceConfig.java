package com.dbtest.dbconfig;

import com.dbtest.base.DataSourceType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class ReadDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(env.getProperty("spring.datasource.read.url"));
            config.setUsername(env.getProperty("spring.datasource.read.username"));
            config.setPassword(env.getProperty("spring.datasource.read.password"));
            config.setDriverClassName(env.getProperty("spring.datasource.read.driver-class-name"));
            return new HikariDataSource(config);
        } catch (Exception e) {
            log.error("error in connection to readDataSource dataSource! caused by : " + e);
            return null;
        }
    }

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(env.getProperty("spring.datasource.readwrite.url"));
            config.setUsername(env.getProperty("spring.datasource.readwrite.username"));
            config.setPassword(env.getProperty("spring.datasource.readwrite.password"));
            config.setDriverClassName(env.getProperty("spring.datasource.readwrite.driver-class-name"));

            return new HikariDataSource(config);
        } catch (Exception e) {
            log.error("error in connection to writeDataSource dataSource! caused by : " + e);
            return null;
        }
    }


    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
            @Qualifier("writeDataSource") DataSource writeDataSource,
            @Qualifier("readDataSource") DataSource readDataSource) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.READ_WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ_ONLY, readDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return routingDataSource;
    }

    @Bean(name = "lazyDataSource")
    @DependsOn({"writeDataSource", "readDataSource", "routingDataSource"})
    public DataSource lazyDataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("lazyDataSource") DataSource lazyDataSource) {
        Properties properties = new Properties();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("spring.jpa.show-sql")));
        vendorAdapter.setDatabase(Database.ORACLE);
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("spring.jpa.properties.hibernate.hbm2ddl.auto", "none");
        //properties.put("hibernate.connection.provider_disables_autocommit", true);
        em.setDataSource(lazyDataSource);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPackagesToScan("com.dbtest.*");
        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) throws SQLException {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    private HikariConfig hikariConfig(DataSource dataSource) {
        HikariConfig hikariConfig = new HikariConfig();
        int cpuCores = Runtime.getRuntime().availableProcessors();
        hikariConfig.setMaximumPoolSize(cpuCores * 4);
        hikariConfig.setDataSource(dataSource);

        hikariConfig.setAutoCommit(false);
        return hikariConfig;
    }

    private HikariDataSource connectionPoolDataSource(DataSource dataSource) {
        return new HikariDataSource(hikariConfig(dataSource));
    }
}
