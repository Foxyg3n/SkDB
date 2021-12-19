package com.blackmc.skdb.database.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Record {
    private HashMap<String, Object> set = new HashMap<>();
    
    public Record(ResultSet rs) throws SQLException {
        for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            set.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
        }
    }

    public Object getValue(String columnLabel) {
        return set.get(columnLabel);
    }

}