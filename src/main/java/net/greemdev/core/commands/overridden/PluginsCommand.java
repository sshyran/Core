package net.greemdev.core.commands.overridden;

import net.greemdev.core.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PluginsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandUtil.warnIf(sender,
                !(sender.isOp() || sender.hasPermission("core.plugins") || CommandUtil.isConsole(sender)),
                "I'm sorry, but you do not have permission to perform this command. " +
                "Please contact the server administrators if you believe that this is in error.")) {
            return true;
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + sender.getName() + " bukkit:pl");
        }
        return true;
    }
}
