package net.greemdev.core.commands;

import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SmpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (CheckUtil.warnIfConsole(sender)) return true;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp smp " + sender.getName());
        sender.sendMessage(ChatColor.AQUA + "Now entering the SMP world.");
        return true;
    }
}
