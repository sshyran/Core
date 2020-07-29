package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.greemdev.core.Core;
import net.greemdev.core.util.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.script.*;
import java.util.Objects;

public class EvalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandUtil.warnIfNotAuthorized(sender, "")) {
            return true;
        }
        if (CommandUtil.warnIfEmptyArgs(sender, args)) {
            return true;
        }
        String code = String.join(" ", args);
        ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
        se.put("server", sender.getServer());
        se.put("jda", DiscordUtil.getJda());
        se.put("dsrv", DiscordSRV.getPlugin());
        se.put("core", Core.get());
        if (!CommandUtil.isConsole(sender)) {
            Player p = CommandUtil.asPlayer(sender);
            se.put("world", Objects.requireNonNull(p).getWorld());
            se.put("player", CommandUtil.asPlayer(sender));
        }

        try {
            Object o = se.eval(code);
            if (o == null) {
                sender.sendMessage(ChatColor.DARK_RED + "Output from Eval: " + ChatColor.RED + "null, but it succeeded");
                return true;
            }
            sender.sendMessage(ChatColor.DARK_RED + "Output from Eval: " + ChatColor.RED + o.toString());
        } catch (ScriptException e) {
            sender.sendMessage(ChatColor.DARK_RED + "That code produced an error: " + ChatColor.RED + e.getMessage());
        }
        return true;
    }
}
