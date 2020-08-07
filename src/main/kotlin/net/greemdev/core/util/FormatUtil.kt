package net.greemdev.core.util

import org.bukkit.ChatColor.*;

public class FormatUtil {
    companion object {
        public fun getMessagePrefix(): String = "" + GRAY + "[" + RED + "Core" + GRAY + "]" + RESET + ": "
        public fun getPermissionError(): String = getMessagePrefix() + DARK_RED + "You do not have permission to use this command."
    }
}