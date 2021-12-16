package com.blackmc.skdb;

import java.io.IOException;

import com.blackmc.skdb.database.DatabaseConfiguration;
import com.blackmc.skdb.database.DatabaseManager;
import com.blackmc.skdb.database.DatabaseType;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class SkDB extends JavaPlugin {

    public static SkDB instance;
    public static SkriptAddon skriptAddon;

    @Override
    public void onEnable() {
        
        //Initialization
        // --

        //Skript
        if(getServer().getPluginManager().getPlugin("Skript") != null) {
            getLogger().info("Skript was found! Registering BlackBedWars skript expansion...");
            skriptAddon = Skript.registerAddon(this);
            try {
                skriptAddon.loadClasses("com.blackmc.blackbedwars.hooks.skript", "classes");
                skriptAddon.loadClasses("com.blackmc.blackbedwars.hooks.skript", "conditions");
                skriptAddon.loadClasses("com.blackmc.blackbedwars.hooks.skript", "effects");
                skriptAddon.loadClasses("com.blackmc.blackbedwars.hooks.skript", "events");
                skriptAddon.loadClasses("com.blackmc.blackbedwars.hooks.skript", "expressions");
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("Skript addon registered!");
        } else {
            getLogger().warning("Could not find Skript! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    public static void main(String[] args) {

        DatabaseConfiguration config = new DatabaseConfiguration(DatabaseType.MYSQL, "localhost", null, "SkDBTest", "root", null, "serverTimezone=Europe/Warsaw");
        boolean isConnected = DatabaseManager.registerDatabaseConnection(config);

        System.out.println(isConnected ? "Database connected" : "Could not connect to the database");

        DatabaseManager.database.close();

    }

    @Override
    public void onDisable() {
        
    }

}