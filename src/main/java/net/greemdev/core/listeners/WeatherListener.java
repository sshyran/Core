package net.greemdev.core.listeners;

import net.greemdev.core.Core;
import net.greemdev.core.util.FormatUtil;
import net.greemdev.core.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.io.File;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.get(), task -> {
            File file = new File("plugins/Core/enableAutoWeather");
            if (!file.exists()) return;
            if (event.toWeatherState()) {
                ServerUtil.getOnlineOps().forEach(p -> {
                    p.sendMessage(FormatUtil.getMessagePrefix() +
                            "Because the file \"enableAutoWeather\" exists in the directory \"plugins/Core\", " +
                            "the weather has been automatically changed from rain to clear.");
                });
                try {
                    Thread.sleep(1000);
                    event.getWorld().setWeatherDuration(0);
                } catch (InterruptedException ignored) {}

            }
        });
    }

}
