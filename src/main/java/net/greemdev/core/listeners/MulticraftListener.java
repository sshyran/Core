package net.greemdev.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class MulticraftListener implements Listener {

    //Stop Multicraft's annoying ass /list spam; because apparently there's no easier way for Multicraft to check if the server is online.
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (event.getCommand().equalsIgnoreCase("list")) {
            event.setCancelled(true);
            System.out.print(" ");
        }
    }

}
