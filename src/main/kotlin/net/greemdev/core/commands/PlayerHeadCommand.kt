package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.util.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class PlayerHeadCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (event.sender.warnIfNotAuthorized("core.playerhead") or
            event.sender.warnIfEmptyArgs(event.args) or
            event.sender.warnIfConsole()) return CommandResult.unsuccessful()

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + event.player.name + " minecraft:player_head{SkullOwner:\"" + event.args.first() + "\"}")
        event.player.sendCoreMessage(
                FormatUtil.getMessagePrefix() + "You've been given " +
                ChatColor.DARK_AQUA + ChatColor.BOLD + event.args.first() +
                ChatColor.RESET + ChatColor.GOLD + "'s head.")
        return CommandResult.successful()
    }

}