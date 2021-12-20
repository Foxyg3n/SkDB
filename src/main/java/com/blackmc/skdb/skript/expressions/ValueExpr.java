package com.blackmc.skdb.skript.expressions;

import javax.annotation.Nullable;

import com.blackmc.skdb.database.Database;
import com.blackmc.skdb.database.DatabaseManager;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ValueExpr extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ValueExpr.class, Object.class, ExpressionType.SIMPLE, "value from %string%");
    }
    Expression<String> queryExpression;

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
        queryExpression = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Value with query expression " + queryExpression.toString(event, debug);
    }

    @Override
    @Nullable
    protected Object[] get(Event event) {
        String query = queryExpression.getSingle(event);
        Database database = DatabaseManager.getDatabase();
        Object value = database.getValueFromQuery(query);
        return new Object[] { value };
    }
    
}
