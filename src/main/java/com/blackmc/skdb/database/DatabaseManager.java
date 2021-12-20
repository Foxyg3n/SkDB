package com.blackmc.skdb.database;

import java.util.NoSuchElementException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

    private static Database database = null;

    public static void registerDatabase(final DatabaseConfiguration config) {

        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setConnectionTimeout(1000);
        hikariConfig.setAutoCommit(true);

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        database = new Database(ds);
    }

    public static Database getDatabase() {
        if(database == null) throw new NoSuchElementException("Attempting to retrieve database when it has not yet been registered");
        // TODO Store the warning
        return database;
    }

}