package net.greemdev.core.commands;

import net.greemdev.core.util.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission("core.name") || sender.isOp())) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
            return true;
        }
        if (CommandUtil.warnIfConsole(sender)) return true;
        Player player = Objects.requireNonNull(CommandUtil.asPlayer(sender));

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (CommandUtil.warnIf(sender, meta == null, "You're not holding anything.") ||
                CommandUtil.warnIf(sender, (player.getExp() - 1) < 0.00, "You must have at least one level to run this command.") ||
                CommandUtil.warnIfEmptyArgs(sender, args)) {
            return true;
        }

        Objects.requireNonNull(meta).setDisplayName(String.join(" ", args)); //meta never can be null at this point but IJ was screaming at me so here you go
        item.setItemMeta(meta);
        player.sendMessage(ChatColor.DARK_AQUA + "Successfully set the item's name in your main hand to " + String.join(" ", args));
        player.setExp(player.getExp() - 1);
        return true;
    }
}
