package net.greemdev.core.listeners

import net.greemdev.core.util.*
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.server.ServerCommandEvent

public class CommandListener: Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        ServerUtil.getOnlineOps().forEach { p: Player ->
            if (p.name != event.player.name) {
                p.sendCoreMessage(ChatColor.DARK_AQUA.toString() + event.player.name
                        + ChatColor.AQUA.toString() + " used command " + ChatColor.DARK_AQUA + event.message)
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public fun onServerCommand(event: ServerCommandEvent) {
        if (event.command == "list") return
        ServerUtil.getOnlineOps().forEach { p: Player ->
                p.sendCoreMessage(ChatColor.DARK_AQUA.toString() + "CONSOLE"
                        + ChatColor.AQUA + " used command " + ChatColor.DARK_AQUA + "/" + event.command)

        }

    }

}