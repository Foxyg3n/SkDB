package com.blackmc.skdb.database;

public enum DatabaseType {
    MYSQL("com.mysql.cj.jdbc.Driver", "org.hibernate.dialect.MySQL5Dialect"),
    ORACLE("oracle.jdbc.OracleDriver", "org.hibernate.dialect.Oracle12cDialect"),
    POSTGRESQL("org.postgresql.Driver", "	org.hibernate.dialect.PostgreSQL95Dialect"),
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver", "org.hibernate.dialect.SQLServer2012Dialect"),
    MARIADB("org.mariadb.jdbc.Driver", "org.hibernate.dialect.MariaDB53Dialect");

    private String driver;
    private String dialect;

    DatabaseType(String driver, String dialect) {
        this.driver = driver;
        this.dialect = dialect;
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

    public String getDialect() {
        return dialect;
    }

    public String getName() {
        return name().toLowerCase();
    }

}