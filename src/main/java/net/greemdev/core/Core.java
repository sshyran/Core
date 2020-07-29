package net.greemdev.core;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.greemdev.core.commands.*;
import net.greemdev.core.listeners.*;

@SuppressWarnings("ALL")
public final class Core extends JavaPlugin {

    private static Core plugin;
    public static Core get() { return plugin; }

    @Override
    public void onEnable() {
        this.plugin = this;
        this.getLogger().info("Loading Core v" + getDescription().getVersion() + "...");
        this.registerEverything();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading Core v" + getDescription().getVersion() + "...");
    }

    private void registerEverything() {
        registerAllCommands();
        registerAllEvents();
    }

    private void registerAllCommands() {
        this.getCommand("PlayerHead").setExecutor(new PlayerHeadCommand());
        this.getCommand("Broadcast").setExecutor(new BroadcastCommand());
        this.getCommand("ConsoleSay").setExecutor(new ConsoleSayCommand());
        this.getCommand("RandomTeleport").setExecutor(new RandomTeleportCommand());
        this.getCommand("Eval").setExecutor(new EvalCommand());
        this.getCommand("Name").setExecutor(new NameCommand());
        this.getCommand("DelayedRestart").setExecutor(new DelayedRestartCommand());
    }

    private void registerAllEvents() {
        PluginManager m = this.getServer().getPluginManager();
        m.registerEvents(new CommandListener(), this);
        m.registerEvents(new WeatherListener(), this);
        m.registerEvents(new MulticraftListener(), this);
    }
}
