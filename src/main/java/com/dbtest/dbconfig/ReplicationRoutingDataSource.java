package com.dbtest.dbconfig;

import com.dbtest.base.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author m.Shahrestanaki @createDate 11/25/2024
 */
@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        try {
            boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            DataSourceType dataSourceType = isReadOnly ? DataSourceType.READ_ONLY : DataSourceType.READ_WRITE;
            log.debug("Current DataSource type: {}", dataSourceType);
            return dataSourceType;
        } catch (Exception e) {

        }
        return null;
    }
}