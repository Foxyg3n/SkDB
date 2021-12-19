package com.blackmc.skdb.skript.effects;

import javax.annotation.Nullable;

import com.blackmc.skdb.database.Database;
import com.blackmc.skdb.database.DatabaseManager;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ExecuteUpdateEff extends Effect {

    static {
        Skript.registerEffect(ExecuteUpdateEff.class, "execute query %string%");
    }
    Expression<String> queryExpression;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        queryExpression = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Execute update query with query expression " + queryExpression.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        String query = queryExpression.getSingle(event);
        Database database = DatabaseManager.getDatabase();
        database.executeUpdate(query);
    }
    
}
