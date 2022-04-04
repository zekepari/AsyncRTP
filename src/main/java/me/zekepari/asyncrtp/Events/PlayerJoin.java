package me.zekepari.asyncrtp.Events;

import me.zekepari.asyncrtp.AsyncRTP;
import me.zekepari.asyncrtp.Utilities.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("NewPlayerRTP.Enabled")) {
            Player player = event.getPlayer();

            if (player.hasPlayedBefore()) { return; }

            Teleporter.issueRTP(player, Bukkit.getWorld(AsyncRTP.getAsyncRTP().getConfig().getString("NewPlayerRTP.RespawnWorld")));
        }
    }
}
