package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import net.greemdev.core.util.CheckUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConsoleSayCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (CheckUtil.warnIfPlayer(sender)) return true;

        DiscordSRV.getPlugin().getMainTextChannel().sendMessage("[Console] \u00BB " + String.join(" ", args)).queue();
        sender.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Console" + ChatColor.GRAY + "]"
                + ChatColor.RESET + ": " + String.join(" ", args));

        return true;
    }
}
