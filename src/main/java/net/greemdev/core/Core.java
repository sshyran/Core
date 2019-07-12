package net.greemdev.core;

import net.greemdev.core.commands.*;
import net.greemdev.core.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public final class Core extends JavaPlugin {

    private static Core plugin;
    public static Core getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        this.getLogger().info("Loading Core...");
        this.getCommand("ConsoleSay").setExecutor(new ConsoleSayCommand());
        this.getCommand("RandomTeleport").setExecutor(new RandomTeleportCommand());
        this.getCommand("Eval").setExecutor(new EvalCommand());
        this.getCommand("Smp").setExecutor(new SmpCommand());
        this.getCommand("Skyblock").setExecutor(new SkyblockCommand());
        this.getCommand("Name").setExecutor(new NameCommand());
        this.getCommand("RankUp").setExecutor(new RankUpCommand());
        this.getCommand("DelayedRestart").setExecutor(new DelayedRestartCommand());
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.plugin = this;
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading Core...");
    }
}
