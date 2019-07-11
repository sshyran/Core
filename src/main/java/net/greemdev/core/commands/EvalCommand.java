package net.greemdev.core.commands;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
            return true;
        }
        String code = String.join(" ", args);
        ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
        se.put("user", sender.getServer().getPlayer(sender.getName()));
        se.put("server", sender.getServer());
        se.put("jda", DiscordUtil.getJda());
        se.put("dsrv", DiscordSRV.getPlugin());
        se.put("luckperms", Bukkit.getServicesManager().getRegistration(LuckPermsApi.class).getProvider());

        try {
            Object o = se.eval(code);
            if (o == null) return true;
            sender.sendMessage(ChatColor.DARK_RED + "Output from Eval: " + ChatColor.RED + o.toString());
        } catch (ScriptException e) {
            sender.sendMessage(ChatColor.DARK_RED + "That code produced an error: " + ChatColor.RED + e.getMessage());
        }
        return true;
    }
}
