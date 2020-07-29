package net.greemdev.core.listeners;

import net.greemdev.core.util.FormatUtil;
import net.greemdev.core.util.ServerUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener { //basically just CommandSpy

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        ServerUtil.getOnlineOps().forEach(p -> {
            if (!p.getName().equalsIgnoreCase(event.getPlayer().getName())) {
                p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + event.getPlayer().getName()
                        + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + event.getMessage());
            }
        });

    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (event.getCommand().equalsIgnoreCase("list")) return;
        ServerUtil.getOnlineOps().forEach(p -> {
            p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "CONSOLE"
                    + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + "/" + event.getCommand());
        });

    }

}
