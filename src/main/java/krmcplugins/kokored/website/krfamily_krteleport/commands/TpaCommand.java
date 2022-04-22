package krmcplugins.kokored.website.krfamily_krteleport.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;

public class TpaCommand implements CommandExecutor {

    Plugin plugin = KrTeleport.getPlug();

    public TpaCommand() {
        if (plugin.getConfig().getBoolean("command.back.enable")) {
            Bukkit.getPluginCommand("back").setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only execute by player!");
            return true;
        }
        Player player = (Player) sender;
        return false;
    }

}
