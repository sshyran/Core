package net.greemdev.core.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ServerUtil {

    private ServerUtil() {}

    public static List<Player> getOnlineOps() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.isOp() || p.hasPermission("*")).collect(Collectors.toList());
    }

    public static List<Player> getOnlineNormalPlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> !p.isOp()).collect(Collectors.toList());
    }

}
