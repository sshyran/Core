package net.greemdev.core.commands

import github.scarsz.discordsrv.DiscordSRV
import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.util.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class BroadcastCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (event.sender.warnIfNotAuthorized("core.broadcast") or
                event.sender.warnIfEmptyArgs(event.args)) return CommandResult.unsuccessful()
        DiscordSRV.getPlugin().mainTextChannel.sendMessage("[**Broadcast**]: " + event.args.join(" ")).queue()
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED.toString() + "[" + ChatColor.DARK_AQUA + ChatColor.BOLD +
                "Broadcast" + ChatColor.RESET + ChatColor.DARK_RED + "]" + ChatColor.GRAY + ": "
                + event.args.join(" "))
        return CommandResult.successful()
    }
}