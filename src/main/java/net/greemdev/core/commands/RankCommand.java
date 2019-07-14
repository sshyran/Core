package net.greemdev.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class RankCommand implements CommandExecutor {

    private static Map<String, String[]> rankMeta = new HashMap<>();

    public RankCommand() {
        rankMeta.put("Redstone", new String[]{
                "- 10 homes (/sethome)", "- Redstone Kit", "- /disposal (/trash)", "- /nick"
        });
        rankMeta.put("Emerald", new String[]{
                "- 25 homes (/sethome)", "- Emerald Kit", "- /workbench (/craft, /wb)", "- /nick (with colors)"
        });
        rankMeta.put("Obsidian", new String[]{
                "- 100 homes (/sethome)", "- Obsidian Kit", "- /enderchest (/ec)", "- /fix", "- /ptime"
        });
        rankMeta.put("Bedrock", new String[]{
                "- 500 homes (/sethome)", "- Bedrock Kit", "- /fly", "- /name (names the item in your hand)", "- /pweather"
        });

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage(ChatColor.DARK_RED + "You didn't provide the correct number of arguments.");
            sender.sendMessage(ChatColor.RED + "Correct usage: " + ChatColor.AQUA + "/rank <rank>");
            return true;
        }

        if (rankMeta.keySet().stream().noneMatch(x -> x.equalsIgnoreCase(args[0]))) {
            sender.sendMessage(ChatColor.DARK_RED + "Unknown rank.");
            sender.sendMessage(ChatColor.RED + "Valid ranks are: " + ChatColor.AQUA + "Redstone, Emerald, Obsidian, Bedrock");
            return true;
        }

        String targetRankPerks = String.join("\n", rankMeta.get(args[0]));

        sender.sendMessage(ChatColor.DARK_AQUA + "Rank " + args[0] + " has the following perks:\n" + targetRankPerks);
        return true;
    }
}
