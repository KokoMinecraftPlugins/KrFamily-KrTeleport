package krmcplugins.kokored.website.krfamily_krteleport.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;
import krmcplugins.kokored.website.krfamily_krteleport.api.LocationAPI;
import krmcplugins.kokored.website.krfamily_krteleport.api.TeleportType;
import krmcplugins.kokored.website.krfamily_krteleport.api.event.TeleportPointRecordEvent;

public class PlayerTeleportEventListener implements Listener {

    public PlayerTeleportEventListener() {
        Bukkit.getPluginManager().registerEvents(this, KrTeleport.getPlug());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        TeleportCause teleportCause = event.getCause();

        if (teleportCause == TeleportCause.COMMAND || teleportCause == TeleportCause.PLUGIN || teleportCause == TeleportCause.SPECTATE) {
            Player player = event.getPlayer();
            Location location = event.getFrom();
    
            TeleportPointRecordEvent teleportPointRecordEvent = new TeleportPointRecordEvent(player, location);
            Bukkit.getPluginManager().callEvent(teleportPointRecordEvent);
    
            if (teleportPointRecordEvent.isCancelled()) {
                return;
            }
    
            LocationAPI.saveBackLocation(player, location, TeleportType.BACK);
        }

    }
    
}
