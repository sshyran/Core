package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.util.*
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class NameCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (event.sender.warnIfNotAuthorized("core.name")
            || event.sender.warnIfConsole()) return CommandResult.unsuccessful()

        val item = event.player.inventory.itemInMainHand
        val meta = item.itemMeta

        if (event.sender.warnIf(meta == null, "You're not holding anything.") ||
                event.sender.warnIfEmptyArgs(event.args)) return CommandResult.unsuccessful()

        meta.setDisplayName(event.args.join(" "))
        item.itemMeta = meta
        event.player.sendCoreMessage(ChatColor.DARK_AQUA.toString() + "Successfully set the item's name in your main hand to \"" + event.args.joinToString(separator = " ") + "\".")
        return CommandResult.successful()
    }
}