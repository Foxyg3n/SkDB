package com.blackmc.skdb.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.blackmc.skdb.database.wrappers.Record;
import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private HikariDataSource ds;

    public Database(HikariDataSource dataSource) {
        this.ds = dataSource;
    }

    public Optional<Connection> getConnection() {
        try {
            return Optional.of(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Object getValueFromQuery(String query) {
        ResultSet rs = executeQuery(query);
        if(rs != null) {
            try {
                rs.next();
                return rs.getObject(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object[] getCollectionFromQuery(String query) {
        ResultSet rs = executeQuery(query);
        if(rs != null) {
            try {
                return new ArrayList<>() {{
                    while(rs.next()) {
                        add(rs.getObject(1));
                    }
                }}.toArray();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Record[] getSetFromQuery(String query) {
        ResultSet rs = executeQuery(query);
        if(rs != null) {
            try {
                return new ArrayList<Record>() {{
                    while(rs.next()) {
                        add(new Record(rs));
                    }
                }}.toArray(new Record[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet executeQuery(String query) {
        try {
            Optional<Connection> optConnection = getConnection();
            if(!optConnection.isPresent()) return null;
            Connection connection = optConnection.get();
            ResultSet rs = connection.createStatement().executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String query) {
        try {
            Optional<Connection> optConnection = getConnection();
            if(!optConnection.isPresent()) return;
            Connection connection = optConnection.get();
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
