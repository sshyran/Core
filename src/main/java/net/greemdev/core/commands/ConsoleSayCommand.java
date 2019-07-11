package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleSayCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(ChatColor.DARK_RED + "You can only use that command via Console.");
            return true;
        }

        DiscordSRV.getPlugin().getMainTextChannel().sendMessage("[Console] \u00BB " + String.join(" ", args)).queue();
        sender.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Console" + ChatColor.GRAY + "]"
                + ChatColor.RESET + ": " + String.join(" ", args));

        return true;
    }
}
