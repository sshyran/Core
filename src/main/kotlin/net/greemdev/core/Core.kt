package net.greemdev.core

import net.greemdev.core.util.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerCommandEvent
import org.bukkit.plugin.java.JavaPlugin

public object Core : JavaPlugin(), Listener {

    @JvmStatic var plugin: Core? = null
    @JvmStatic fun get(): Core {
        if (plugin == null) {
            throw java.lang.NullPointerException("Core#plugin's value was null.")
        }
        return plugin!!.get()
    }

    public override fun onEnable() {
        plugin = this
        logger.info("Loading Core v" + description.version + "...")
        registerEverything()
    }

    public override fun onDisable() {
        logger.info("Unloading Core v" + description.version + "...")
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public fun onServerCommand(event: ServerCommandEvent) {
        if (event.command == "list") {
            event.isCancelled = true
            print(" ")
        }
    }

}