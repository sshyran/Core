package net.greemdev.core.commands

import github.scarsz.discordsrv.DiscordSRV
import github.scarsz.discordsrv.util.DiscordUtil
import net.greemdev.core.Core
import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.util.CommandUtil
import net.greemdev.core.util.FormatUtil
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import javax.script.ScriptEngineManager
import javax.script.ScriptException

public class EvalCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return executeCommand(CommandEvent.from(sender, command, label, args))
    }

    private fun executeCommand(event: CommandEvent): Boolean {
        if (CommandUtil.warnIfNotAuthorized(event.sender, "")) return true;
        if (CommandUtil.warnIfEmptyArgs(event.sender, event.args)) return true;
        val code = event.args.joinToString(separator = " ")
        val se = ScriptEngineManager().getEngineByName("js")
        se.put("server", event.sender.server)
        se.put("jda", DiscordUtil.getJda())
        se.put("dsrv", DiscordSRV.getPlugin())
        se.put("core", Core.get())
        if (!CommandUtil.isConsole(event.sender)) {
            se.put("world", event.player.world)
            se.put("player", event.player)
        }

        try {
            val o = se.eval(code)
            if (o == null) {
                event.sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_RED + "Output from Eval: " + ChatColor.RED + "null, but it succeeded")
                return true
            }
            event.sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_RED + "Output from Eval: " + ChatColor.RED + o.toString())
        } catch (e: ScriptException) {
            event.sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.DARK_RED + "That code produced an error: " + ChatColor.RED + e.message)
        }
        return true
    }

}