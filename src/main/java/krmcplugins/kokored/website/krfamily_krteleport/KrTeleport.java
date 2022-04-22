package krmcplugins.kokored.website.krfamily_krteleport;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krfamily_krteleport.commands.BackCommand;
import krmcplugins.kokored.website.krfamily_krteleport.database.TableCreate;
import krmcplugins.kokored.website.krfamily_krteleport.listener.PlayerDeathEventListener;
import krmcplugins.kokored.website.krfamily_krteleport.listener.PlayerTeleportEventListener;
import krmcplugins.kokored.website.krfamily_krteleport.util.Message;

public final class KrTeleport extends JavaPlugin {

    static String SERVER_NAME;
    Connection connection;
    
    @Override
    public void onEnable() {

        try {
            Properties props = new Properties();
            props.load(new FileInputStream("server.properties"));
            SERVER_NAME = props.getProperty("server-name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // init sql
        connection = KrCore.getMySQL();
        new TableCreate(this);

        new PlayerDeathEventListener();
        new PlayerTeleportEventListener();

        new BackCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlug() {
        return getPlugin(KrTeleport.class);
    }
    public Connection getMySQL() {
        return connection;
    }
    public static String getServerName() {
        return SERVER_NAME;
    }

    public void sendMessage(Player player, String messagee) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
    public void sendMessage(Player player, String message, Boolean prefix) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        if (prefix) {
            sendMessage(player, message);
        }else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    public void sendMessage(CommandSender sender, String messagee) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
}
