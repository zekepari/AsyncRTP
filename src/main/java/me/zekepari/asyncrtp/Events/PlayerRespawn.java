package me.zekepari.asyncrtp.Events;

import me.zekepari.asyncrtp.AsyncRTP;
import me.zekepari.asyncrtp.Utilities.Teleporter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("RespawnRTP.Enabled")) {
            Player player = event.getPlayer();

            Teleporter.issueRTP(player);
        }
    }
}
