package net.greemdev.core.ranks;

import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RankUpSystem {

    private static LuckPermsApi api = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class).getProvider();

    public static void rankUp(String username, CommandSender sender) {
        User user = api.getUser(username);

        if (user == null) {
            sender.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.DARK_RED + "Could not find player.");
            return;
        }

        Group newGroup = null;
        switch (user.getPrimaryGroup().toLowerCase()) {
            case "default":
                newGroup = api.getGroup("redstone");
                break;
            case "redstone":
                newGroup = api.getGroup("emerald");
                break;
            case "emerald":
                newGroup = api.getGroup("obsidian");
                break;
            case "obsidian":
                newGroup = api.getGroup("bedrock");
                break;
            default:
                break;
        }

        if (newGroup == null) {
            sender.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.DARK_RED + "Player's current group (" + user.getPrimaryGroup() + ") cannot be ranked up from.");
            return;
        }

        Bukkit.getPlayer(user.getName()).sendMessage(ChatColor.DARK_AQUA + "You ranked up from " + ChatColor.RED
                + user.getPrimaryGroup() + ChatColor.DARK_AQUA + " to " + ChatColor.RED + newGroup.getDisplayName() + ChatColor.DARK_AQUA + "!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + username + " permission unset group." + user.getPrimaryGroup());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + username + " permission set group." + newGroup.getName() + " true");
    }

}
