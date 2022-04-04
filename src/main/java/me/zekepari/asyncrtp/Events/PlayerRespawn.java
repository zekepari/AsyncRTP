package me.zekepari.asyncrtp.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
    }
}
