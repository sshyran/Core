package net.greemdev.core.util

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

public class CommandUtil {

    companion object {
        public fun isConsole(s: CommandSender): Boolean = s is ConsoleCommandSender
        public fun isAdmin(s: CommandSender): Boolean = isConsole(s) && s.isOp
        public fun isAuthorized(s: CommandSender, permission: String): Boolean = isAdmin(s) && s.hasPermission(permission)
        public fun isPlayer(s: CommandSender): Boolean = !isConsole(s)
        public fun warnIfConsole(s: CommandSender): Boolean = warnIf(s, isConsole(s), "This command cannot be used from Console.")
        public fun warnIfPlayer(s: CommandSender): Boolean =  warnIf(s, isConsole(s), "This command may not be used by a player.")

        public fun warnIfNotAuthorized(s: CommandSender, permission: String): Boolean {
            if (permission.isEmpty()) {
                if (isAdmin(s)) {
                    return false
                } else {
                    noPermission(s)
                    return true
                }
            }

            if (isAuthorized(s, permission)) return false
            else {
                noPermission(s)
                return true;
            }
        }

        public fun warnIfEmptyArgs(s: CommandSender, args: Array<out String>): Boolean = warnIf(s, args.isEmpty(), "You didn't provide any arguments.")

        public fun noPermission(s: CommandSender) = s.sendMessage(FormatUtil.getPermissionError())

        public fun asConsole(sender: CommandSender): ConsoleCommandSender = sender as ConsoleCommandSender
        public fun asPlayer(sender: CommandSender): Player = sender as Player

        public fun warnIf(sender: CommandSender, condition: Boolean, warningMessage: String): Boolean {
            if (condition) {
                sender.sendMessage(ChatColor.DARK_RED.toString() + warningMessage)
                return true
            }
            return false
        }
    }

}