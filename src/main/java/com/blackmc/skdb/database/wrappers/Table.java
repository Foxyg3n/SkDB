package com.blackmc.skdb.database.wrappers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Table {
    
    private ArrayList<Record> set = new ArrayList<>();
 
    public Table(Record[] records) {
        this.set = new ArrayList<>(Arrays.asList(records));
    }

    public Table(ArrayList<HashMap<String, Object>> set) {
        this(set.stream().map(hashMap -> new Record(hashMap)).toArray(Record[]::new));
    }

    public ArrayList<Record> getRecords() {
        return set;
    }

    public Record[] getRecordsAsArray() {
        return set.toArray(new Record[0]);
    }

    public static Table fromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        ArrayList<HashMap<String, Object>> tableMap = new ArrayList<>();
        while(rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            for(int i = 1; i <= columns; i++) {           
                row.put(metaData.getColumnName(i),rs.getObject(i));
            }
        }
        return new Table(tableMap);
    }

}