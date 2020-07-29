package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import net.greemdev.core.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandUtil.warnIfNotAuthorized(sender, "core.broadcast")) {
            return true;
        }
        if (CommandUtil.warnIfEmptyArgs(sender, args)) {
            return true;
        }
        DiscordSRV.getPlugin().getMainTextChannel().sendMessage("[**Broadcast**]: " + String.join(" ", args)).queue();
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.DARK_AQUA + ChatColor.BOLD +
                "Broadcast" + ChatColor.RESET + ChatColor.DARK_RED + "]" + ChatColor.GRAY + ": "
                + String.join(" ", args));
        return true;
    }
}
