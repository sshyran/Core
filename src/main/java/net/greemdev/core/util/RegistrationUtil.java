package net.greemdev.core.util;

import net.greemdev.core.commands.*;
import net.greemdev.core.listeners.CommandListener;
import net.greemdev.core.listeners.PlayerJoinListener;

import static net.greemdev.core.Core.getPlugin;

@SuppressWarnings("ALL")
public class RegistrationUtil {

    private RegistrationUtil() {} //restrict instantiation as this is purely a static utility class

    public static void registerEverything() {
        registerAllCommands();
        registerAllEvents();
    }

    public static void registerAllCommands() {
        getPlugin().getCommand("ConsoleSay").setExecutor(new ConsoleSayCommand());
        getPlugin().getCommand("RandomTeleport").setExecutor(new RandomTeleportCommand());
        getPlugin().getCommand("Eval").setExecutor(new EvalCommand());
        getPlugin().getCommand("Smp").setExecutor(new SmpCommand());
        getPlugin().getCommand("Skyblock").setExecutor(new SkyblockCommand());
        getPlugin().getCommand("Name").setExecutor(new NameCommand());
        getPlugin().getCommand("RankUp").setExecutor(new RankUpCommand());
        getPlugin().getCommand("DelayedRestart").setExecutor(new DelayedRestartCommand());
        getPlugin().getCommand("Rank").setExecutor(new RankCommand());
        getPlugin().getCommand("Suggest").setExecutor(new SuggestCommand());
    }

    public static void registerAllEvents() {
        getPlugin().getServer().getPluginManager().registerEvents(new PlayerJoinListener(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new CommandListener(), getPlugin());
    }

}
