package net.greemdev.core.commands;

import net.greemdev.core.ranks.RankUpSystem;
import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RankUpCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.isOp() || CheckUtil.isConsole(sender))) {
            sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Not enough permission.");
            Bukkit.getLogger().warning(sender.getName() + " tried using the RankUp command.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Too many/not enough arguments provided.");
            sender.sendMessage(ChatColor.DARK_RED + "Usage: " + ChatColor.RED + "/rankup <user>");
            return true;
        }

        RankUpSystem.rankUp(args[0], Bukkit.getPlayer(sender.getName()));
        return true;
    }
}
