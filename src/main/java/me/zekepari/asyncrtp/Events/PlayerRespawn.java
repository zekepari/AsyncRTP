package me.zekepari.asyncrtp.Events;

import me.zekepari.asyncrtp.AsyncRTP;
import me.zekepari.asyncrtp.Utilities.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("RespawnRTP.Enabled")) {
            Player player = event.getPlayer();

            if (AsyncRTP.getAsyncRTP().getConfig().getString("RespawnRTP.RespawnWorld").isEmpty()) {
                Teleporter.issueRTP(player, player.getWorld());
                return;
            }

            Teleporter.issueRTP(player, Bukkit.getWorld(AsyncRTP.getAsyncRTP().getConfig().getString("RespawnRTP.RespawnWorld")));
        }
    }
}
