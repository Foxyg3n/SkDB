package com.blackmc.skdb.database;

public enum DatabaseType {
    MYSQL("com.mysql.cj.jdbc.Driver"),
    ORACLE("oracle.jdbc.OracleDriver"),
    POSTGRESQL("org.postgresql.Driver"),
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    MARIADB("org.mariadb.jdbc.Driver");

    private String driver;

    DatabaseType(String driver) {
        this.driver = driver;
    }

    public static DatabaseType getByName(String name) {
        try {
            return DatabaseType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getName() {
        return name().toLowerCase();
    }

}