package me.not_black.itemsapi;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public final class MainAPI {

    public static ItemStack getItemStack(@NotNull String key) {
        return ItemsAPI.itemsManager.getItemStack(key);
    }

    public static boolean importItemStack(@NotNull String key, @NotNull ItemStack itemStack) throws RuntimeException{
        return ItemsAPI.itemsManager.importItemStack(key, itemStack);
    }

}
