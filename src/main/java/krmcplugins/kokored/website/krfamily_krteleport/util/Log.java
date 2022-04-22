package krmcplugins.kokored.website.krfamily_krteleport.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

import krmcplugins.kokored.website.krfamily_krteleport.KrTeleport;

public class Log {

    public static void info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[" + KrTeleport.getPlug().getName() + "] " + msg);
    }

    public static void warn(String msg) {
        Bukkit.getLogger().log(Level.WARNING, "[" + KrTeleport.getPlug().getName() + "] " + msg);
    }

    public static void error(String msg) {
        Bukkit.getLogger().log(Level.SEVERE, "[" + KrTeleport.getPlug().getName() + "] " + msg);
    }
    
}
