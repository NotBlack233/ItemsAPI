package me.not_black.itemsapi.files;

import me.not_black.itemsapi.ItemsAPI;
import me.not_black.itemsapi.logging.IALogger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ItemsManager {

    private final IALogger logger;
    private final Map<String, ItemStack> items = new HashMap<>();
    private final File folder = new File(ItemsAPI.getPlugin().getDataFolder(), "items");

    public ItemsManager() {
        this.logger = new IALogger(ItemsAPI.getDefaultLogger(), "Items");
        logger.info("Loading items...");

        if (!folder.exists()) {
            logger.info("Items directory does not exist, creating...");
            try {
                folder.mkdir();
            } catch (Exception e) {
                logger.severe("Cannot create items directory!");
            }
        } else if (folder.exists() && !folder.isDirectory()) {
            logger.severe("There is already a file named items!");
            throw new RuntimeException("There is already a file named items!");
        } else logger.info("Items directory exists.");

        loadItems();
    }

    private int loadItems() {
        items.clear();
        int successItems = 0;

        if (folder.listFiles() != null)
            for (File file : folder.listFiles()) {
                if (!file.getName().endsWith(".yml")) continue;
                int result = loadFile(file);
                logger.info("Loaded " + result + " items from file " + file.getName() + " .");
                successItems += result;
            }

        logger.info("Loaded " + successItems + " in total.");
        return successItems;
    }

    private int loadFile(@NotNull File file) {
        items.clear();
        int cnt = 0;
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (String key : yamlConfiguration.getKeys(false)) {
            ItemStack is;
            try {
                is = yamlConfiguration.getItemStack(key);
                items.put(key, is);
                cnt++;
            } catch (Exception e) {
                logger.warning("Failed to load item " + key + " from file " + file.getName());
            }
        }
        return cnt;
    }

    public boolean importItemStack(@NotNull String key, @NotNull ItemStack itemStack) throws RuntimeException {
        File f = new File(folder, key+".yml");
        if (items.containsKey(key) || f.exists())
            return false;
        try {
            f.createNewFile();
        } catch (IOException e) {
            logger.severe("Failed to create new file!");
            throw new RuntimeException("Failed to create new file", e);
        }
        items.put(key, itemStack);
        YamlConfiguration yamlConfiguration=YamlConfiguration.loadConfiguration(f);
        yamlConfiguration.set(key, itemStack);
        try {
            yamlConfiguration.save(f);
        } catch (IOException e) {
            logger.severe("Failed to save imported item!");
            throw new RuntimeException("Failed to save imported item", e);
        }
        return true;
    }

    @Nullable
    public ItemStack getItemStack(@NotNull String key) {
        return items.get(key);
    }

    public boolean reload() {
        try {
            loadItems();
            logger.info("Items reloaded.");
        } catch (Exception e) {
            logger.warning("Failed to reload items!");
            return false;
        }
        return true;
    }

}
