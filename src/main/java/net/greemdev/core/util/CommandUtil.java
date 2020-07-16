package net.greemdev.core.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandUtil {

    private CommandUtil() {} //restrict instantiation as this is purely a static utility class

    public static boolean isConsole(CommandSender s) {
        return s instanceof ConsoleCommandSender;
    }

    public static boolean warnIfConsole(CommandSender s) {
        if (isConsole(s)) {
            s.sendMessage(ChatColor.DARK_RED + "This command cannot be used from Console.");
            return true;
        }
        return false;
    }

    public static void noPermission(CommandSender s) {
        s.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
    }

    public static boolean warnIfPlayer(CommandSender s) {
        if (!(isConsole(s))) {
            s.sendMessage(ChatColor.DARK_RED + "This command cannot be used by a Player.");
            return true;
        }
        return false;
    }

    public static boolean warnIfEmptyArgs(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_RED + "You didn't provide any arguments.");
            return true;
        }
        return false;
    }

    public static Player asPlayer(CommandSender sender) {
        if (isConsole(sender)) return null;
        else return (Player)sender;
    }

    public static ConsoleCommandSender asConsole(CommandSender sender) {
        if (!isConsole(sender)) return null;
        else return (ConsoleCommandSender)sender;
    }

    public static boolean warnIf(CommandSender sender, boolean condition, String warningMessage) {
        if (condition) {
            sender.sendMessage(ChatColor.DARK_RED + warningMessage);
            return true;
        }
        return false;
    }

}
