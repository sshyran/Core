package net.greemdev.core.util

import org.bukkit.ChatColor.*

public object FormatUtil {
    @JvmStatic public fun getMessagePrefix(): String = "" + GRAY + "[" + RED + "Core" + GRAY + "]" + RESET + ": "
    @JvmStatic public fun getPermissionError(): String = getMessagePrefix() + DARK_RED + "You do not have permission to use this command."
}