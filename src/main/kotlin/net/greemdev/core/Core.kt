package net.greemdev.core

import net.greemdev.core.commands.EvalCommand
import net.greemdev.core.commands.NameCommand
import net.greemdev.core.commands.PlayerHeadCommand
import net.greemdev.core.commands.RandomTeleportCommand
import net.greemdev.core.util.registerCoreEvents
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerCommandEvent
import org.bukkit.plugin.java.JavaPlugin
import java.lang.NullPointerException

public object Core : JavaPlugin(), Listener {

    @JvmStatic var plugin: Core? = null
    @JvmStatic fun get(): Core {
        if (plugin == null) {
            throw NullPointerException("Core#plugin's value was null.")
        }
        return plugin!!.get()
    }

    public override fun onEnable() {
        this.plugin = this
        this.logger.info("Loading Core v" + description.version + "...")
        this.registerEverything()
    }

    public override fun onDisable() {
        this.logger.info("Unloading Core v" + description.version + "...")
    }

    private fun registerEverything() {
        get().server.pluginManager.registerCoreEvents()
        registerCommands()
    }

    private fun registerCommands() {
        this.getCommand("Eval")?.setExecutor(EvalCommand())
        this.getCommand("RandomTeleport")?.setExecutor(RandomTeleportCommand())
        this.getCommand("Name")?.setExecutor(NameCommand())
        this.getCommand("PlayerHead")?.setExecutor(PlayerHeadCommand())
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public fun onServerCommand(event: ServerCommandEvent) {
        if (event.command == "list") {
            event.isCancelled = true
            print(" ")
        }
    }

}