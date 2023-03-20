package me.not_black.itemsapi;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;

public final class MainAPI {

    public static ItemStack getItemStack(String key) {
        return ItemsAPI.itemsManager.getItemStack(key);
    }

    public static boolean importItemStack(String key, ItemStack itemStack) {
        return ItemsAPI.itemsManager.importItemStack(key, itemStack);
    }

}
