package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.util.CommandUtil
import net.greemdev.core.util.FormatUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class PlayerHeadCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return executeCommand(CommandEvent.from(sender, command, label, args))
    }

    private fun executeCommand(event: CommandEvent): Boolean {
        if (CommandUtil.warnIfNotAuthorized(event.sender, "core.playerhead")) return true
        if (CommandUtil.warnIfEmptyArgs(event.sender, event.args)) return true
        if (CommandUtil.warnIfConsole(event.sender)) return true

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + event.player.name + " minecraft:player_head{SkullOwner:\"" + event.args.first() + "\"}")
        event.player.sendMessage(FormatUtil.getMessagePrefix() +
                ChatColor.GOLD + "You've been given " +
                ChatColor.DARK_AQUA + ChatColor.BOLD + event.args.first() +
                ChatColor.RESET + ChatColor.GOLD + "'s head.")
        return true
    }

}