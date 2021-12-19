package com.blackmc.skdb.skript.expressions;

import javax.annotation.Nullable;

import com.blackmc.skdb.database.Database;
import com.blackmc.skdb.database.DatabaseManager;
import com.blackmc.skdb.database.wrappers.Record;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class SetExpr extends SimpleExpression<Record> {

    static {
        Skript.registerExpression(SetExpr.class, Record.class, ExpressionType.SIMPLE, "set from %string%");
    }
    Expression<String> queryExpression;

    @Override
    public Class<? extends Record> getReturnType() {
        return Record.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        queryExpression = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Set with query expression " + queryExpression.toString(event, debug);
    }

    @Override
    @Nullable
    protected Record[] get(Event event) {
        String query = queryExpression.getSingle(event);
        Database database = DatabaseManager.getDatabase();
        Record[] set = database.getSetFromQuery(query);
        return set;
    }
    
}