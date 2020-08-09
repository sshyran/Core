package net.greemdev.core.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.stream.Collectors

public class ServerUtil {
    companion object {
        public fun getOnlineOps(): List<Player> {
            return Bukkit.getOnlinePlayers().stream().filter { p: Player ->
                p.isAdmin()
            }.collect(Collectors.toList())
        }

        public fun getOnlineNormalPlayers(): List<Player> {
            return Bukkit.getOnlinePlayers().stream().filter { p: Player ->
                !p.isAdmin()
            }.collect(Collectors.toList())
        }
    }
}