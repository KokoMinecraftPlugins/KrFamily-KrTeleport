package krmcplugins.kokored.website.krfamily_krteleport.database;

import java.sql.SQLException;
import java.sql.Statement;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;
import krmcplugins.kokored.website.krfamily_krteleport.util.Log;

public class TableCreate {

    KrTeleport krTeleport;
    String databaseName = KrCore.getSQLName();

    public TableCreate(KrTeleport krTeleport) {
        this.krTeleport = krTeleport;
        Log.info("Loading KrTeleport mysql data tables");
        
        try {
            teleport_locations();

            Log.info("Data table loaded!");
        } catch (SQLException e) {
            Log.error("Fail to load Data table: " + e.getMessage());
        }
    }
    
    private void teleport_locations() throws SQLException {
        Statement table = krTeleport.getMySQL().createStatement();
        String table_code = "CREATE TABLE IF NOT EXISTS " + databaseName + ".teleport_locations (" +
            "record_name CHAR(255) PRIMARY KEY," +
            "record_type CHAR(255) NOT NULL," +
            "player CHAR(16) NOT NULL," +
            "cross_server BOOLEAN NOT NULL," +
            "server CHAR(255) NOT NULL," +
            "loca_world CHAR(255) NOT NULL," +
            "loca_x DOUBLE NOT NULL," +
            "loca_y DOUBLE NOT NULL," +
            "loca_z DOUBLE NOT NULL," +
            "loca_yaw FLOAT NOT NULL," +
            "loca_pitch FLOAT NOT NULL" +
            ") CHARSET=utf8;";
        table.execute(table_code);
    }

}
