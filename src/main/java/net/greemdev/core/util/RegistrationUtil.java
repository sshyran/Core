package net.greemdev.core.util;

import net.greemdev.core.commands.*;
import net.greemdev.core.commands.overridden.PluginsCommand;
import net.greemdev.core.listeners.CommandListener;

import static net.greemdev.core.Core.getPlugin;

@SuppressWarnings("ALL")
public class RegistrationUtil {

    private RegistrationUtil() {} //restrict instantiation as this is purely a static utility class

    public static void registerEverything() {
        registerAllCommands();
        registerAllEvents();
    }

    public static void registerAllCommands() {
        getPlugin().getCommand("Plugins").setExecutor(new PluginsCommand());
        getPlugin().getCommand("Broadcast").setExecutor(new BroadcastCommand());
        getPlugin().getCommand("ConsoleSay").setExecutor(new ConsoleSayCommand());
        getPlugin().getCommand("RandomTeleport").setExecutor(new RandomTeleportCommand());
        getPlugin().getCommand("Eval").setExecutor(new EvalCommand());
        getPlugin().getCommand("Name").setExecutor(new NameCommand());
        getPlugin().getCommand("DelayedRestart").setExecutor(new DelayedRestartCommand());
    }

    public static void registerAllEvents() {
        getPlugin().getServer().getPluginManager().registerEvents(new CommandListener(), getPlugin());
    }

}
