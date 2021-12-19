package com.blackmc.skdb.skript.expressions;

import javax.annotation.Nullable;

import com.blackmc.skdb.database.wrappers.Record;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ValueFromSetExpr extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ValueFromSetExpr.class, Object.class, ExpressionType.SIMPLE, "%string% from %record%");
    }
    Expression<String> columnLabelExpression;
    Expression<Record> setExpression;

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        columnLabelExpression = (Expression<String>) exprs[0];
        setExpression = (Expression<Record>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Value from set " + setExpression.toString(event, debug);
    }

    @Override
    @Nullable
    protected Object[] get(Event event) {
        String columnLabel = columnLabelExpression.getSingle(event);
        Record record = setExpression.getSingle(event);
        return new Object[] { record.getValue(columnLabel) };
    }
    
}