package net.greemdev.core.commands;

import net.greemdev.core.Core;
import net.greemdev.core.util.CommandUtil;
import net.greemdev.core.util.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DelayedRestartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandUtil.warnIfNotAuthorized(sender, "core.delayedrestart")) {
            return true;
        }

        Bukkit.getScheduler().runTask(Core.get(), () -> {
            try {
                //this is so fucking ugly i know please dont flame me
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting in 5 seconds.");
                Thread.sleep(1000);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting in 4 seconds.");
                Thread.sleep(1000);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting in 3 seconds.");
                Thread.sleep(1000);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting in 2 seconds.");
                Thread.sleep(1000);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting in 1 second.");
                Thread.sleep(1000);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "csay Server restarting now.");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
            } catch (InterruptedException e) {
                sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_RED + "Restarting got interrupted: " + e.getMessage());
            }

        });
        return true;
    }

}
