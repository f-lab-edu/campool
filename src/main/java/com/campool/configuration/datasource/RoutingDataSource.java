package com.campool.configuration.datasource;

import static com.campool.configuration.DataSourceConfiguration.MASTER;
import static com.campool.configuration.DataSourceConfiguration.SLAVE;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ?
                SLAVE : MASTER;
    }
}
