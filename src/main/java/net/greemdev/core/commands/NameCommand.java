package net.greemdev.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("core.name") || sender.isOp())) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
            return true;
        }
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Console can't use this command, jackass.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.DARK_RED + "You didn't provide a name.");
            return true;
        }

        ItemStack item = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();

        if (item == null) {
            sender.sendMessage(ChatColor.DARK_RED + "You're not holding anything.");
            return true;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            sender.sendMessage(ChatColor.DARK_RED + "You're not holding anything.");
            return true;
        }

        meta.setDisplayName(String.join(" ", args));
        item.setItemMeta(meta);
        sender.sendMessage(ChatColor.DARK_AQUA + "Successfully set the item's name in your main hand to " + String.join(" ", args));
        return true;
    }
}
