package com.blackmc.skdb.database;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseConfiguration {

    private String username;
    private String password;
    private String driver;
    private String dialect;
    private String url;

    public DatabaseConfiguration(DatabaseType type, String ip, String port, String database, String username, String password, String... options) {
        this.username = username;
        this.password = password != null ? password : "";
        this.driver = type.getDriver();
        this.dialect = type.getDialect();
        this.url = buildURLString(type, ip, port, database, new ArrayList<String>(Arrays.asList(options)));
    }
 
    public DatabaseConfiguration(DatabaseType type, String url, String username, String password) {
        this.username = username;
        this.password = password != null ? password : "";
        this.driver = type.getDriver();
        this.dialect = type.getDialect();
        this.url = url;
    }

    public static String buildURLString(DatabaseType type, String ip, String port, String database, ArrayList<String> options) {
        if(type.equals(DatabaseType.MYSQL)) {
            String url = "jdbc:" + type.getName() + "://" + ip + (port != null ? ":" + port : "") + "/" + database;
            for(int i = 0; i < options.size(); i++) {
                url += (i == 0 ? "?" + options.get(i) : "&" + options.get(i));
            }
            return url;
        } else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDialect() {
        return dialect;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

}