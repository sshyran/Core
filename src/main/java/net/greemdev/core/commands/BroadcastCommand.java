package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("core.broadcast") || CheckUtil.isConsole(sender) || sender.isOp())) {
            CheckUtil.warn(sender);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("You didn't give anything to broadcast.");
            return false;
        }
        DiscordSRV.getPlugin().getMainTextChannel().sendMessage("[**Broadcast**]: " + String.join(" ", args)).queue();
        Bukkit.getServer().broadcastMessage(String.join(" ", args));
        return true;
    }
}
