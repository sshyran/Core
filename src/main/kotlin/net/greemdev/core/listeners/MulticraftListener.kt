package net.greemdev.core.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerCommandEvent

public class MulticraftListener: Listener {

    @EventHandler
    public fun onServerCommand(event: ServerCommandEvent) {
        if (event.command == "list") {
            event.isCancelled = true;
            print(" ")
        }
    }

}