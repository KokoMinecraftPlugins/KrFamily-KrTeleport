package krmcplugins.kokored.website.krfamily_krteleport.database.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krfamily_krteleport.api.TeleportType;

public class TeleportLocationsTable {
    
    static String database = KrCore.getSQLName();
    static String table = "teleport_locations";
    
    public static Boolean locationExists(String player, TeleportType teleportType) {
        try {
            PreparedStatement ps = KrCore.getMySQL().prepareStatement(
            "SELECT COUNT(*) FROM " + database + "." + table +" WHERE record_name=?;");
            ps.setString(1, teleportType.name() + ":" + player);
            ResultSet result = ps.executeQuery();
            result.next();
            Integer resultInt = result.getInt("COUNT(*)");
            if (resultInt == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addLocationRecord(String record_name, String record_type, String player, Boolean cross_server, String server,
    String loca_world, Double loca_x, Double loca_y, Double loca_z, Float loca_yaw, Float loca_pitch) {
        try {
            PreparedStatement ps = KrCore.getMySQL().prepareStatement(
            "INSERT INTO " + database + "." + table +
            "(record_name,record_type,player,cross_server,server,loca_world,loca_x,loca_y,loca_z,loca_yaw,loca_pitch) VALUES (?,?,?,?,?,?,?,?,?,?,?);");

            ps.setString(1, record_name);
            ps.setString(2, record_type);
            ps.setString(3, player);
            ps.setBoolean(4, cross_server);
            ps.setString(5, server);
            ps.setString(6, loca_world);
            ps.setDouble(7, loca_x);
            ps.setDouble(8, loca_y);
            ps.setDouble(9, loca_z);
            ps.setFloat(10, loca_yaw);
            ps.setFloat(11, loca_pitch);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Location getLocation(String player, TeleportType teleportType) {
        if (!(locationExists(player, teleportType))) {
            return null;
        }
        try {
            PreparedStatement ps = KrCore.getMySQL().prepareStatement(
            "SELECT * FROM " + database + "." + table +" WHERE record_name=?;");
            ps.setString(1, teleportType.name() + ":" + player);
            ResultSet result = ps.executeQuery();
            result.next();

            Location location = new Location(
                Bukkit.getWorld(result.getString("loca_world")),
                Double.valueOf(result.getString("loca_x")),
                Double.valueOf(result.getString("loca_y")),
                Double.valueOf(result.getString("loca_z")),
                Float.valueOf(result.getString("loca_yaw")),
                Float.valueOf(result.getString("loca_pitch"))
            );

            return location;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteRecord(String record_name) {
        try {
            PreparedStatement ps = KrCore.getMySQL().prepareStatement(
            "DELETE FROM " + database + "." + table +
            " WHERE record_name=?;");

            ps.setString(1, record_name);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
