package net.greemdev.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SuggestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.RED + "Suggest things here: " + ChatColor.DARK_AQUA + "https://forms.gle/qLQayZKwzWn7R4UBA");
        return true;
    }
}
