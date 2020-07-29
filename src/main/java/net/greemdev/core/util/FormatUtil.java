package net.greemdev.core.util;

import static org.bukkit.ChatColor.*;

public class FormatUtil {

    private FormatUtil() {
    }

    public static String getMessagePrefix() {
        return GRAY + "[" + RED + "Core" + GRAY + "]" + RESET + ": ";
    }

    public static String getPermissionError() {
        return getMessagePrefix() + DARK_RED + "You do not have permission to use this command.";
    }
}
