package net.greemdev.core.commands

import github.scarsz.discordsrv.DiscordSRV
import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.util.*
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class ConsoleSayCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (!event.sender.isAdmin() || event.sender.warnIfEmptyArgs(event.args)) return CommandResult.unsuccessful()

        DiscordSRV.getPlugin().mainTextChannel.sendMessage("[Console] \u00BB " + event.args.join(" ")).queue()
        Bukkit.getServer().broadcastMessage(ChatColor.GRAY.toString() + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Console" + ChatColor.GRAY + "]"
                + ChatColor.RESET + ": " + event.args.joinToString(separator = " "))

        return CommandResult.successful()
    }

}