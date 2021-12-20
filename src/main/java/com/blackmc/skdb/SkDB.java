package com.blackmc.skdb;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class SkDB extends JavaPlugin {

    public static SkDB instance;
    public static SkriptAddon skriptAddon;

    @Override
    public void onEnable() {

        //Skript
        if(getServer().getPluginManager().getPlugin("Skript") != null) {
            getLogger().info("Skript was found! Registering SkDB addon...");
            skriptAddon = Skript.registerAddon(this);
            try {
                skriptAddon.loadClasses("com.blackmc.skdb.skript", "classes");
                skriptAddon.loadClasses("com.blackmc.skdb.skript", "conditions");
                skriptAddon.loadClasses("com.blackmc.skdb.skript", "effects");
                skriptAddon.loadClasses("com.blackmc.skdb.skript", "events");
                skriptAddon.loadClasses("com.blackmc.skdb.skript", "expressions");
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("SkDB addon registered!");
        } else {
            getLogger().warning("Could not find Skript! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

}