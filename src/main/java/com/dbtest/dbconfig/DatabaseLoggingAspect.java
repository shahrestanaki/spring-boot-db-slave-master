package com.dbtest.dbconfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author m.Shahrestanaki @createDate 11/26/2024
 */

@Aspect
@Component
@Slf4j
public class DatabaseLoggingAspect {
    private final DataSource dataSource;

    public DatabaseLoggingAspect(@Qualifier("lazyDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Before("execution(* org.springframework.data.repository.CrudRepository.*(..))")
    public void logDatabaseConnection(JoinPoint joinPoint) {
        try (Connection connection = dataSource.getConnection()) {
            log.info("Connection URL: {} and User: {} with Executing SQL: {}", connection.getMetaData().getURL(), connection.getMetaData().getUserName(), joinPoint.getSignature().toShortString());
        } catch (SQLException e) {
            log.error("Error while getting connection info", e);
        }
    }
}