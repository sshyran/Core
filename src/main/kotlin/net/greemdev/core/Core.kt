package net.greemdev.core

import net.greemdev.core.commands.EvalCommand
import net.greemdev.core.commands.NameCommand
import net.greemdev.core.commands.PlayerHeadCommand
import net.greemdev.core.commands.RandomTeleportCommand
import net.greemdev.core.listeners.CommandListener
import net.greemdev.core.listeners.MulticraftListener
import org.bukkit.plugin.java.JavaPlugin
import java.lang.NullPointerException

public object Core : JavaPlugin() {

    @JvmStatic var plugin: Core? = null
    @JvmStatic fun get(): Core {
        if (plugin == null) {
            throw NullPointerException("Core#plugin's value was null.")
        }
        return plugin!!.get()
    }

    public override fun onEnable() {
        this.plugin = this;
        this.logger.info("Loading Core v" + description.version + "...")
        this.registerEverything()
    }

    public override fun onDisable() {
        this.logger.info("Unloading Core v" + description.version + "...")
    }

    private fun registerEverything() {
        registerEvents()
        registerCommands()
    }

    private fun registerEvents() {
        val m = this.server.pluginManager;
        m.registerEvents(CommandListener(), this)
        m.registerEvents(MulticraftListener(), this)
    }

    private fun registerCommands() {
        this.getCommand("Eval")?.setExecutor(EvalCommand())
        this.getCommand("RandomTeleport")?.setExecutor(RandomTeleportCommand())
        this.getCommand("Name")?.setExecutor(NameCommand())
        this.getCommand("PlayerHead")?.setExecutor(PlayerHeadCommand())
    }

}