package com.blackmc.skdb.skript.effects;

import javax.annotation.Nullable;

import com.blackmc.skdb.database.DatabaseConfiguration;
import com.blackmc.skdb.database.DatabaseManager;
import com.blackmc.skdb.database.DatabaseType;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class RegisterDatabaseEff extends Effect {

    static {
        Skript.registerEffect(RegisterDatabaseEff.class, "register database with %string%, %string%, %number%, %string%, %string%, %string%");
    }
    Expression<String> dbtypeExpression;
    Expression<String> ipExpression;
    Expression<Number> portExpression;
    Expression<String> dbnameExpression;
    Expression<String> usernameExpression;
    Expression<String> passwordExpression;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        dbtypeExpression = (Expression<String>) exprs[0];
        ipExpression = (Expression<String>) exprs[1];
        portExpression = (Expression<Number>) exprs[2];
        dbnameExpression = (Expression<String>) exprs[3];
        usernameExpression = (Expression<String>) exprs[4];
        passwordExpression = (Expression<String>) exprs[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Execute update query with query expression "
            + ipExpression.toString(event, debug)
            + portExpression.toString(event, debug)
            + dbnameExpression.toString(event, debug)
            + usernameExpression.toString(event, debug)
            + passwordExpression.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        DatabaseType type = DatabaseType.getByName(dbtypeExpression.getSingle(event));
        String ip = ipExpression.getSingle(event);
        Number port = portExpression.getSingle(event);
        String dbname = dbnameExpression.getSingle(event);
        String username = usernameExpression.getSingle(event);
        String password = passwordExpression.getSingle(event);

        DatabaseConfiguration config = new DatabaseConfiguration(type, ip, port + "", dbname, username, (password.equals("") ? null : password));
        DatabaseManager.registerDatabase(config);
    }
}
