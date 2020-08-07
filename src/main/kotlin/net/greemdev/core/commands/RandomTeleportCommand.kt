package net.greemdev.core.commands

import net.greemdev.core.objects.CommandEvent
import net.greemdev.core.objects.TeleportCoords
import net.greemdev.core.util.CommandUtil
import net.greemdev.core.util.FormatUtil
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

public class RandomTeleportCommand: CommandExecutor {

    private final val random: Random = Random()

    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return executeCommand(CommandEvent.from(sender, command, label, args))
    }

    private fun executeCommand(event: CommandEvent): Boolean {
        if (CommandUtil.warnIfNotAuthorized(event.sender, "core.randomteleport")) return true
        if (CommandUtil.warnIfConsole(event.sender)) return true

        var coords: TeleportCoords
        do {
            coords = this.findDestination(event.player)
        } while (coords.block.isLiquid)

        event.player.teleport(Location(event.player.world, coords.x, coords.y, coords.z))
        event.sender.sendMessage(FormatUtil.getMessagePrefix() + ChatColor.RED + "Teleporting you to " + ChatColor.BLUE
                + coords + ChatColor.GOLD + "!")
        return true;
    }

    private fun findDestination(player: Player): TeleportCoords {
        val x = (this.random.nextInt(50000 - -50000) + -50000).toDouble()
        val z = (this.random.nextInt(50000 - -50000) + -50000).toDouble()
        val block = player.world.getHighestBlockAt(x.toInt(), z.toInt())
        val y = (block.y + 1).toDouble()

        return TeleportCoords.from(x, y, z, block)
    }

}