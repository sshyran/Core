package net.greemdev.core;

import org.bukkit.plugin.java.JavaPlugin;
import net.greemdev.core.commands.*;
import net.greemdev.core.commands.overridden.*;
import net.greemdev.core.listeners.*;

@SuppressWarnings("ALL")
public final class Core extends JavaPlugin {

    private static Core plugin;
    public static Core getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        this.plugin = this;
        this.getLogger().info("Loading Core...");
        this.registerEverything();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading Core...");
    }

    private static final Core core = Core.getPlugin();

    private void registerEverything() {
        registerAllCommands();
        registerAllEvents();
    }

    private void registerAllCommands() {
        core.getCommand("PlayerHead").setExecutor(new PlayerHeadCommand());
        core.getCommand("Plugins").setExecutor(new PluginsCommand());
        core.getCommand("Broadcast").setExecutor(new BroadcastCommand());
        core.getCommand("ConsoleSay").setExecutor(new ConsoleSayCommand());
        core.getCommand("RandomTeleport").setExecutor(new RandomTeleportCommand());
        core.getCommand("Eval").setExecutor(new EvalCommand());
        core.getCommand("Name").setExecutor(new NameCommand());
        core.getCommand("DelayedRestart").setExecutor(new DelayedRestartCommand());
    }

    private void registerAllEvents() {
        core.getServer().getPluginManager().registerEvents(new CommandListener(), core);
    }
}
