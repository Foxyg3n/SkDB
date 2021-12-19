package com.blackmc.skdb.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.bukkit.Bukkit;

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
        if(database == null) Bukkit.getLogger().warning("Attempting to retrive database when it's not registered");
        return database;
    }

}