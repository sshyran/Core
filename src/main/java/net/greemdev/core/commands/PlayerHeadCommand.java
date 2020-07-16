package net.greemdev.core.commands;

import net.greemdev.core.util.CommandUtil;
import net.greemdev.core.util.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerHeadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandUtil.warnIfConsole(sender) || CommandUtil.warnIfEmptyArgs(sender, args)) return true;
        if (!(sender.hasPermission("core.playerhead") || sender.isOp())) {
            CommandUtil.noPermission(sender);
            return true;
        }
        Player p = Objects.requireNonNull(CommandUtil.asPlayer(sender));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "minecraft:give " + p.getName() + " minecraft:player_head{SkullOwner:\"" + args[0] + "\"}");
        p.sendMessage(FormatUtil.getMessagePrefix() +
                ChatColor.GOLD + "You've been given " +
                ChatColor.DARK_AQUA + ChatColor.BOLD + args[0] +
                ChatColor.RESET + ChatColor.GOLD + "'s head.");
        return true;
    }
}
