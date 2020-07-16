package net.greemdev.core.commands;

import net.greemdev.core.util.CommandUtil;
import net.greemdev.core.util.FormatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class RandomTeleportCommand implements CommandExecutor {

    public RandomTeleportCommand() {
        this.random = new Random();
    }

    private final Random random;

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, @NotNull String[] args) {
        if (CommandUtil.warnIfConsole(sender)) return true;
        Player player  = Objects.requireNonNull(CommandUtil.asPlayer(sender));

        int x = this.random.nextInt(20000 - -20000) + -20000;
        int z = this.random.nextInt(20000 - -20000) + -20000;
        int y = player.getWorld().getHighestBlockAt(x, z).getY() + 1;

        player.teleport(new Location(player.getWorld(), x, y, z));
        sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Teleporting you to " + x + ", " + (y-1) + ", " + z + "!");
        return true;

    }
}
