package net.greemdev.core.util

import net.greemdev.core.Core
import net.greemdev.core.listeners.CommandListener
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.objects.TeleportCoords
import org.bukkit.ChatColor
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.PluginManager
import java.util.*

private val random: Random = Random()

public fun CommandSender.isConsole(): Boolean = this is ConsoleCommandSender
public fun CommandSender.isAdmin(): Boolean = this.isConsole() || this.isOp
public fun CommandSender.isAuthorized(permission: String): Boolean = this.isAdmin() || this.hasPermission(permission)
public fun CommandSender.isPlayer(): Boolean = this is Player
public fun CommandSender.warnIfConsole(): Boolean = warnIf(this.isConsole(), "This command cannot be used from Console.")
public fun CommandSender.warnIfPlayer(): Boolean = warnIf(this.isPlayer(), "This command may not be used by a player.")

public fun CommandSender.warnIfNotAuthorized(permission: String): Boolean {
    return if (permission.isEmpty()) {
        if (this.isAdmin()) {
            false
        } else {
            this.noPermission()
            true
        }
    }
    else if (this.isAuthorized(permission)) false
    else {
        this.noPermission()
        true
    }
}

public fun CommandSender.warnIfEmptyArgs(args: Array<out String>): Boolean = this.warnIf(args.isEmpty(), "You didn't provide any arguments.")
public fun CommandSender.noPermission() = this.sendMessage(FormatUtil.getPermissionError())
public fun CommandSender.asConsole(): ConsoleCommandSender = this as ConsoleCommandSender
public fun CommandSender.asPlayer(): Player = this as Player

public fun CommandSender.warnIf(condition: Boolean, warningMessage: String): Boolean {
    if (condition) {
        this.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_RED + warningMessage)
        return true
    }
    return false
}

public fun <T> Array<out T>.join(sep: String): String = this.joinToString(separator = sep)
public fun Any?.toString(): String = this?.toString() ?: "null"
public fun CommandSender.sendCoreMessage(msg: String) = this.sendMessage(FormatUtil.getMessagePrefix() + msg)

public fun Player.findRandomDestination(): TeleportCoords {
    val x = (random.nextInt(50000 - -50000) + -50000).toDouble()
    val z = (random.nextInt(50000 - -50000) + -50000).toDouble()
    val block = this.world.getHighestBlockAt(x.toInt(), z.toInt())
    val y = (block.y + 1).toDouble()

    return TeleportCoords.from(x, y, z, block)
}

public fun PluginManager.registerCoreEvents() {
    val core = Core.get()
    this.registerEvents(CommandListener(), core)
    this.registerEvents(core, core)
}

public fun CommandExecutor.analyzeResult(result: CommandResult): Boolean {
    return result.successful
}
