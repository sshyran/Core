package net.greemdev.core.commands;

import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("core.name") || sender.isOp())) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
            return true;
        }
        if (CheckUtil.warnIfConsole(sender)) return true;
        Player player = (Player)sender;

        if (args.length < 1) {
            sender.sendMessage(ChatColor.DARK_RED + "You didn't provide a name.");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if ((player.getExp() - 1) < 0.00) {
            player.sendMessage(ChatColor.DARK_RED + "You must have at least one level to run this command.");
            return true;
        }

        if (meta == null) {
            player.sendMessage(ChatColor.DARK_RED + "You're not holding anything.");
            return true;
        }

        meta.setDisplayName(String.join(" ", args));
        item.setItemMeta(meta);
        player.sendMessage(ChatColor.DARK_AQUA + "Successfully set the item's name in your main hand to " + String.join(" ", args));
        player.setExp(player.getExp() - 1);
        return true;
    }
}
