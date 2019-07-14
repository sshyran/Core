package net.greemdev.core.util;

import static org.bukkit.ChatColor.*;

public class FormatUtil {

    private FormatUtil() {} //restrict instantiation as this is purely a static utility class

    public static String getMessagePrefix() {
        return GRAY + "[" + RED + "Core" + GRAY + "]" + RESET + " ";
    }

}
