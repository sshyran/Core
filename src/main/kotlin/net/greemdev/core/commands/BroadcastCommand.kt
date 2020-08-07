package net.greemdev.core.commands

import github.scarsz.discordsrv.DiscordSRV
import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.util.CommandUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class BroadcastCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return executeCommand(CommandEvent.from(sender, command, label, args))
    }

    private fun executeCommand(event: CommandEvent): Boolean {
        if (CommandUtil.warnIfNotAuthorized(event.sender, "core.broadcast") ||
                CommandUtil.warnIfEmptyArgs(event.sender, event.args)) return true
        DiscordSRV.getPlugin().mainTextChannel.sendMessage("[**Broadcast**]: " + event.args.joinToString(separator = " ")).queue()
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED.toString() + "[" + ChatColor.DARK_AQUA + ChatColor.BOLD +
                "Broadcast" + ChatColor.RESET + ChatColor.DARK_RED + "]" + ChatColor.GRAY + ": "
                + event.args.joinToString(separator = " "))
        return true
    }
}