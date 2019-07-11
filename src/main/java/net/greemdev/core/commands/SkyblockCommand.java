package net.greemdev.core.commands;

import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SkyblockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (CheckUtil.warnIfConsole(sender)) return true;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp skyblock " + sender.getName());
        sender.sendMessage(ChatColor.AQUA + "Now entering the Skyblock world.");
        return true;
    }
}
