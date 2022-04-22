package krmcplugins.kokored.website.krfamily_krteleport.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;
import krmcplugins.kokored.website.krfamily_krteleport.database.tables.TeleportLocationsTable;

public class LocationAPI {

    private static String SERVER_NAME = KrTeleport.getServerName();

    public static void saveBackLocation(Player player, Location location, TeleportType teleportType) {
        String record_name = teleportType.name() + ":" + player.getName();
        String record_type = teleportType.name();
        String player1 = player.getName();
        Boolean cross_server = false;
        String server = SERVER_NAME;
        String loca_world = location.getWorld().getName();
        Double loca_x = location.getX();
        Double loca_y = location.getY();
        Double loca_z = location.getZ();
        Float loca_yaw = location.getYaw();
        Float loca_pitch = location.getPitch();
        if (locationExists(player1, teleportType)) {
            deleteRecord(record_name);
        }
        TeleportLocationsTable.addLocationRecord(record_name, record_type, player1, cross_server, server, loca_world, loca_x, loca_y, loca_z, loca_yaw, loca_pitch);
    }

    public static Location getLocation(Player player, TeleportType teleportType) {
        return TeleportLocationsTable.getLocation(player.getName(), teleportType);
    }

    public static Boolean locationExists(String player, TeleportType teleportType) {
        return TeleportLocationsTable.locationExists(player, teleportType);
    }

    public static void deleteRecord(String record_name) {
        TeleportLocationsTable.deleteRecord(record_name);
    }
    
}
