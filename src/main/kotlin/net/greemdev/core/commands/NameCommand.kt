package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.util.CommandUtil
import net.greemdev.core.util.FormatUtil
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class NameCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return executeCommand(CommandEvent.from(sender, command, label, args))
    }

    private fun executeCommand(event: CommandEvent): Boolean {
        if (CommandUtil.warnIfNotAuthorized(event.sender, "core.name")) return true;
        if (CommandUtil.warnIfConsole(event.sender)) return true;

        val item = event.player.inventory.itemInMainHand
        val meta = item.itemMeta

        if (CommandUtil.warnIf(event.sender, meta == null, "You're not holding anything.") ||
                CommandUtil.warnIfEmptyArgs(event.sender, event.args)) return true

        meta.setDisplayName(event.args.joinToString(separator = " "))
        item.itemMeta = meta
        event.player.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_AQUA + "Successfully set the item's name in your main hand to \"" + event.args.joinToString(separator = " ") + "\".")
        return true
    }
}