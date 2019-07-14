package net.greemdev.core.listeners;

import net.greemdev.core.util.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.stream.Collectors;

public class CommandListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        for (Player p : Bukkit.getOnlinePlayers().stream().filter(Player::isOp).collect(Collectors.toList())) {
            if (p.getName().equalsIgnoreCase(event.getPlayer().getName())) continue;
            p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + event.getPlayer().getName()
                    + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + event.getMessage());
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        for (Player p : Bukkit.getOnlinePlayers().stream().filter(Player::isOp).collect(Collectors.toList())) {
            p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "CONSOLE"
                    + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + "/" + event.getCommand());
        }
    }

}
