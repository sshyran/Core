package net.greemdev.core.commands.overridden;

import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.isOp() || sender.hasPermission("core.plugins") || CheckUtil.isConsole(sender))) {
            sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. " +
                    "Please contact the server administrators if you believe that this is in error.");
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + sender.getName() + " bukkit:pl");
        }
        return true;
    }
}
