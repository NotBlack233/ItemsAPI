package me.not_black.itemsapi.logging;

import org.bukkit.ChatColor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IALogger {

    private final Logger logger;
    private final String module;
    private final String prefix;

    public IALogger(Logger logger, String module) {
        this.logger=logger;
        this.module=module;
        this.prefix=ChatColor.AQUA+" < "+ChatColor.RESET+module+ChatColor.AQUA+" > "+ChatColor.RESET;
    }

    public void info(String msg) {
        logger.info(prefix+msg);
    }

    public void warning(String msg) {
        logger.warning(prefix+msg);
    }

    public void severe(String msg) {
        logger.severe(prefix+msg);
    }
}
