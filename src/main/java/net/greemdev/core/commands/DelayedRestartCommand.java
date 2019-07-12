package net.greemdev.core.commands;

import net.greemdev.core.Core;
import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelayedRestartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("core.delayedrestart") || CheckUtil.isConsole(sender) || sender.isOp())) {
            CheckUtil.warn(sender);
            return true;
        }

        Bukkit.getScheduler().runTask(Core.getPlugin(), () -> {
            try { //this is so fucking ugly i dont even know
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
            } catch (InterruptedException ignored) {}

        });
        return true;
    }

}
