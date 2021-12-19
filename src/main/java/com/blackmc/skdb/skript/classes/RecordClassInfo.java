package com.blackmc.skdb.skript.classes;

import com.blackmc.skdb.database.wrappers.Record;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;

public class RecordClassInfo {
    
    static {
        Classes.registerClass(new ClassInfo<>(Record.class, "record")
            .user("records?")
            .name("Record")
            .description("represents a database record")
            .examples("loop set from \"SELECT * FROM `table` WHERE columnLabel = 'value'\":")
            .defaultExpression(new EventValueExpression<>(Record.class))
        );
    }

}
