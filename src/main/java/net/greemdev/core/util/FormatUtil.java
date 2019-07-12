package net.greemdev.core.util;

import org.bukkit.ChatColor;

public class FormatUtil {

    private FormatUtil() {} //restrict instantiation as this is purely a static utility class

    public static String getMessagePrefix() {
        return ChatColor.GRAY + "[" + ChatColor.RED + "Core" + ChatColor.GRAY + "]" + ChatColor.RESET + " ";
    }

}
