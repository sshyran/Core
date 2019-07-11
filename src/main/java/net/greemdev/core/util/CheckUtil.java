package net.greemdev.core.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CheckUtil {

    private CheckUtil() {} //restrict instantiation as this is purely a static utility class

    public static boolean isConsole(CommandSender s) {
        return s instanceof ConsoleCommandSender;
    }

    public static boolean warnIfConsole(CommandSender s) {
        if (isConsole(s)) {
            s.sendMessage(ChatColor.DARK_RED + "This command may not be used from Console.");
            return true;
        }
        return false;
    }

    public static boolean warnIfPlayer(CommandSender s) {
        if (!(isConsole(s))) {
            s.sendMessage(ChatColor.DARK_RED + "This command only be used from Console.");
            return true;
        }
        return false;
    }

}
