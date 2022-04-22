package krmcplugins.kokored.website.krfamily_krteleport.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.Plugin;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;
import krmcplugins.kokored.website.krfamily_krteleport.api.LocationAPI;
import krmcplugins.kokored.website.krfamily_krteleport.api.TeleportType;

public class BackCommand implements CommandExecutor {

    Plugin plugin = KrTeleport.getPlug();

    public BackCommand() {
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
        Location location = LocationAPI.getLocation(player, TeleportType.BACK);

        if (location == null) return true;
        Location before = player.getLocation();
        location.getChunk().load();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (plugin.getConfig().getBoolean("allow-move") == false && !(before == player.getLocation())) {
                    return;
                }
                player.teleport(location, TeleportCause.PLUGIN);
            }
        }, (plugin.getConfig().getLong("teleport-cd") * 20));

        return true;
    }
    
}
