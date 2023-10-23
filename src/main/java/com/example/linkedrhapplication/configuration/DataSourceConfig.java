package com.example.linkedrhapplication.configuration;


import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;";

    //  Database credentials
    static final String USER = "ale";
    static final String PASS = "Ocr@2019";
    private static DataSource datasource;
    private static final BasicDataSource basicDataSource;

    static {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(DB_URL);
    }

    public static DataSource getInstance() {
        return basicDataSource;
    }
}
