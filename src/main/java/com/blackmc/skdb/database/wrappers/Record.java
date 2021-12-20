package com.blackmc.skdb.database.wrappers;

import java.util.HashMap;

public class Record {
    
    private HashMap<String, Object> row = new HashMap<>();

    public Record(HashMap<String, Object> row) {
        this.row = row;
    }

    public Object getValue(String columnLabel) {
        return row.get(columnLabel);
    }

    public HashMap<String, Object> getRow() {
        return row;
    }

}