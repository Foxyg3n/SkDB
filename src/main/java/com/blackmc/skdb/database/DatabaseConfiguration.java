package com.blackmc.skdb.database;

public class DatabaseConfiguration {

    private String driver;
    private String username;
    private String password;
    private String url;

    public DatabaseConfiguration(DatabaseType type, String ip, String port, String database, String username, String password) {
        this(type, buildURLString(type, ip, port, database), username, password);
    }
 
    public DatabaseConfiguration(DatabaseType type, String url, String username, String password) {
        this.driver = type.getDriver();
        this.username = username;
        this.password = password != null ? password : "";
        this.url = url;
    }

    private static String buildURLString(DatabaseType type, String ip, String port, String database) {
        if(type.equals(DatabaseType.MYSQL)) {
            return "jdbc:" + type.getName() + "://" + ip + (port != null ? ":" + port : "") + "/" + database;
        } else {
            return null;
        }
    }

    public String getDriver() {
        return driver;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

}