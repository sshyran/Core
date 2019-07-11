package net.greemdev.core.commands;

import net.greemdev.core.util.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;

public class RandomTeleportCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (CheckUtil.warnIfConsole(sender)) return true;

        World targetWorld = Bukkit.getWorld("SMP");
        Random r = new Random();
        int x = r.nextInt(20000 - -20000) + -20000;
        int z = r.nextInt(20000 - -20000) + -20000;
        int y = Bukkit.getWorld("SMP").getHighestBlockAt(x, z).getY()+1;

        sender.getServer().getPlayer(sender.getName()).teleport(new Location(targetWorld, x, y, z));
        sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Teleporting you to " + x + ", " + (y-1) + ", " + z + "!");
        return true;

    }
}
