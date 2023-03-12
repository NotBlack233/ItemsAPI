package me.not_black.itemsapi;

import me.not_black.itemsapi.files.ItemsManager;
import me.not_black.itemsapi.logging.IALogger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public final class ItemsAPI extends JavaPlugin {

    private static Plugin plugin;
    private static Logger defaultLogger;
    private IALogger logger;
    static ItemsManager itemsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin=this;
        defaultLogger=this.getLogger();
        logger = new IALogger(defaultLogger,"Main");
        itemsManager=new ItemsManager();
        logger.info("Plugin loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        logger.info("Plugin unloaded.");
    }

    public static Logger getDefaultLogger() {
        return defaultLogger;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void reload() {
        // TODO reload
    }
}
