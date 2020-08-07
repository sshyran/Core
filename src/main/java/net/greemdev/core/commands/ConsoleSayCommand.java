package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import net.greemdev.core.util.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ConsoleSayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, @NotNull String[] args) {
        if (!CommandUtil.isAdmin(sender)) return true;
        if (CommandUtil.warnIfEmptyArgs(sender, args)) return true;

        DiscordSRV.getPlugin().getMainTextChannel().sendMessage("[Console] \u00BB " + String.join(" ", args)).queue();
        sender.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Console" + ChatColor.GRAY + "]"
                + ChatColor.RESET + ": " + String.join(" ", args));

        return true;
    }
}
