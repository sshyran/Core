package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.CommandResult
import net.greemdev.core.objects.TeleportCoords
import net.greemdev.core.util.*
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

public class RandomTeleportCommand: CommandExecutor {

    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.analyzeResult(this.executeCommand(CommandEvent.from(sender, command, label, args)))
    }

    private fun executeCommand(event: CommandEvent): CommandResult {
        if (event.sender.warnIfNotAuthorized("core.randomteleport") or
            event.sender.warnIfConsole()) return CommandResult.unsuccessful()

        var coords: TeleportCoords
        do {
            coords = event.player.findRandomDestination()
        } while (coords.highestBlock.isLiquid)

        event.player.teleport(Location(event.player.world, coords.x, coords.y, coords.z))
        event.player.sendCoreMessage(ChatColor.RED.toString() + "Teleporting you to " + ChatColor.BLUE
                + coords + ChatColor.RED + "!")
        return CommandResult.successful()
    }
}