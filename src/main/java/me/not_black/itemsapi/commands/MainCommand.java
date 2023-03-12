package me.not_black.itemsapi.commands;

import me.not_black.itemsapi.ItemsAPI;
import me.not_black.itemsapi.MainAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements TabExecutor {

    private final String NO_PERMISSION= ChatColor.RED+ "This command requires permission: "+ChatColor.RESET;

    private final List<String> BOTH = new ArrayList<String>() {{add("import");add("reload");}};
    private final List<String> IMPORT = new ArrayList<String>() {{add("import");}};
    private final List<String> RELOAD = new ArrayList<String>() {{add("reload");}};

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("itemsapi.use")) {
            switch (args.length) {
                case 1: {
                    if(args[0].equals("reload")) {
                        if(sender.hasPermission("itemsapi.reload")) {
                            sender.sendMessage(ChatColor.GREEN+"Reloading...");
                            ItemsAPI.reload();
                            sender.sendMessage(ChatColor.GREEN+"Reloaded!");
                        } else sendNoPermission(sender, "itemsapi.reload");
                    } else sendHelp(sender);
                    break;
                }
                case 2: {
                    if(args[0].equals("import")) {
                        if(sender.hasPermission("itemsapi.import")) {
                            if(sender instanceof Player) {
                                boolean result= MainAPI.importItemStack(args[1], ((Player) sender).getInventory().getItemInMainHand());
                                if(result) sender.sendMessage(ChatColor.GREEN+"Successfully imported item "+args[1]);
                                else sender.sendMessage(ChatColor.RED+"Failed to import item "+args[1]);
                            } else sender.sendMessage(ChatColor.RED+"A player is required to run this command.");
                        } else sendNoPermission(sender,"itemsapi.import");
                    } else sendHelp(sender);
                    break;
                }
                case 0:
                default: sendHelp(sender);break;
            }
        } else sendNoPermission(sender,"itemsapi.use");
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length==1) {
            if(args[0].length()==0) return BOTH;
            if(args[0].charAt(0)=='i') return IMPORT;
            if(args[0].charAt(0)=='r') return RELOAD;
        }
        return null;
    }

    private void sendHelp(@NotNull CommandSender sender) {
        // TODO complete sendHelp
    }

    private void sendNoPermission(@NotNull CommandSender sender, @NotNull String permission) {
        sender.sendMessage(NO_PERMISSION+permission);
    }
}
