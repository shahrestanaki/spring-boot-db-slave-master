package com.dbtest.base;

/**
 * @author m.Shahrestanaki @createDate 11/25/2024
 */
public enum DataSourceType {
    READ_ONLY(0),READ_WRITE(1);

    DataSourceType(int datasourcetype) {
         this.datasourcetype = datasourcetype;
    }

    final int datasourcetype;
}
