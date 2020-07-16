package net.greemdev.core.commands;

import net.greemdev.core.objects.RandomTeleportCoords;
import net.greemdev.core.util.CommandUtil;
import net.greemdev.core.util.FormatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
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

        RandomTeleportCoords coords;
        do {
            coords = this.findDestination(player);
        } while (coords.getBlock().isLiquid());


        player.teleport(new Location(player.getWorld(), coords.getX(), coords.getY(), coords.getZ()));
        sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Teleporting you to " + coords + "!");
        return true;

    }

    private RandomTeleportCoords findDestination(Player player) {
        int x = this.random.nextInt(20000 - -20000) + -20000;
        int z = this.random.nextInt(20000 - -20000) + -20000;
        Block block = player.getWorld().getHighestBlockAt(x, z);
        int y = block.getY() + 1;


        return new RandomTeleportCoords(x, y, z, block);
    }
}
