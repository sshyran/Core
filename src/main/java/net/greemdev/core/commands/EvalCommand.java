package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.greemdev.core.util.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            CommandUtil.noPermission(sender);
            return true;
        }
        if (CommandUtil.warnIfEmptyArgs(sender, args)) {
            return true;
        }
        String code = String.join(" ", args);
        ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
        se.put("user", CommandUtil.asPlayer(sender));
        se.put("server", sender.getServer());
        se.put("jda", DiscordUtil.getJda());
        se.put("dsrv", DiscordSRV.getPlugin());

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
