package net.greemdev.core;

import net.greemdev.core.util.RegistrationUtil;
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
        this.plugin = this;
        RegistrationUtil.registerEverything();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading Core...");
    }
}
