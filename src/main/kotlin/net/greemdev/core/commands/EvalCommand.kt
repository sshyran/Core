package net.greemdev.core.commands

import github.scarsz.discordsrv.DiscordSRV
import github.scarsz.discordsrv.util.DiscordUtil
import net.greemdev.core.Core
import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.util.*
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import javax.script.ScriptEngineManager
import javax.script.ScriptException

public class EvalCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (event.sender.warnIfNotAuthorized("") or event.sender.warnIfEmptyArgs(event.args)) return CommandResult.unsuccessful()
        val code = event.args.join(" ")
        val se = ScriptEngineManager().getEngineByName("js")
        se.put("server", event.sender.server)
        se.put("jda", DiscordUtil.getJda())
        se.put("dsrv", DiscordSRV.getPlugin())
        se.put("core", Core.get())
        if (!event.sender.isPlayer()) {
            se.put("world", event.player.world)
            se.put("player", event.player)
        }

        try {
            val o = se.eval(code)
            event.sender.sendCoreMessage(FormatUtil.getMessagePrefix() + "Output from Eval: " + ChatColor.RED + o)
        } catch (e: ScriptException) {
            event.sender.sendCoreMessage(FormatUtil.getMessagePrefix() + "That code produced an error: " + ChatColor.RED + e.message)
        }
        return CommandResult.successful()
    }

}