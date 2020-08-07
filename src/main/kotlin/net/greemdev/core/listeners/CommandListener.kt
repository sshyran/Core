package net.greemdev.core.listeners

import net.greemdev.core.util.FormatUtil
import net.greemdev.core.util.ServerUtil
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.server.ServerCommandEvent

public class CommandListener: Listener {

    @EventHandler
    public fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        ServerUtil.getOnlineOps().forEach { p: Player ->
            if (p.name != event.player.name) {
                p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + event.player.name
                        + ChatColor.AQUA.toString() + " used command " + ChatColor.DARK_AQUA + event.message)
            }
        }
    }

    @EventHandler
    public fun onServerCommand(event: ServerCommandEvent) {
        if (event.command == "list") return;
        ServerUtil.getOnlineOps().forEach { p: Player ->
                p.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "CONSOLE"
                        + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + "/" + event.command)

        }

    }

}