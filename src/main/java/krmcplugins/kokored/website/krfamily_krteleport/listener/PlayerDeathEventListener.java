package krmcplugins.kokored.website.krfamily_krteleport.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;
import krmcplugins.kokored.website.krfamily_krteleport.api.LocationAPI;
import krmcplugins.kokored.website.krfamily_krteleport.api.TeleportType;
import krmcplugins.kokored.website.krfamily_krteleport.api.event.DeathPointRecordEvent;

public class PlayerDeathEventListener implements Listener {

    public PlayerDeathEventListener() {
        Bukkit.getPluginManager().registerEvents(this, KrTeleport.getPlug());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = event.getEntity().getLocation();

        DeathPointRecordEvent deathPointRecordEvent = new DeathPointRecordEvent(player, location);
        Bukkit.getPluginManager().callEvent(deathPointRecordEvent);

        if (deathPointRecordEvent.isCancelled()) {
            return;
        }

        LocationAPI.saveBackLocation(player, location, TeleportType.BACK);
    }
    
}
