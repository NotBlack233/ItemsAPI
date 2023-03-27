package me.not_black.itemsapi;

import me.not_black.itemsapi.commands.MainCommand;
import me.not_black.itemsapi.files.ItemsManager;
import me.not_black.itemsapi.logging.IALogger;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class ItemsAPI extends JavaPlugin {

    private static Plugin plugin;
    private static Logger defaultLogger;
    private static IALogger logger;
    static ItemsManager itemsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        if(!this.getDataFolder().exists()) {
            logger.info("Plugin folder does not exist, creating...");
            this.getDataFolder().mkdir();
        }

        defaultLogger = this.getLogger();
        logger = new IALogger(defaultLogger, "Main");
        itemsManager = new ItemsManager();

        if (!addCommand("itemsapi", new MainCommand())) logger.warning("Failed to register command!");

        logger.info("Plugin loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin unloaded.");
    }

    private boolean addCommand(@NotNull String command, @NotNull TabExecutor tabExecutor) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd == null) return false;
        cmd.setExecutor(tabExecutor);
        cmd.setTabCompleter(tabExecutor);
        return true;
    }

    public static Logger getDefaultLogger() {
        return defaultLogger;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void reload() {
        boolean r = itemsManager.reload();
        if (r) logger.info("Plugin reloaded.");
        else logger.warning("Failed to reload!");
    }
}
