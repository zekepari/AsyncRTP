package me.zekepari.asyncrtp.Events;

import me.zekepari.asyncrtp.AsyncRTP;
import me.zekepari.asyncrtp.Utilities.MessageService;
import me.zekepari.asyncrtp.Utilities.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (!AsyncRTP.getAsyncRTP().getConfig().getBoolean("RespawnRTP.Enabled")) {
            return;
        }

        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("RespawnRTP.CheckForBed")) {
            MessageService.sendMessage(player, AsyncRTP.getAsyncRTP().getConfig().getString("Messages.Bed"));
            return;
        }

        if (AsyncRTP.getAsyncRTP().getConfig().getString("RespawnRTP.RespawnWorld").isEmpty()) {
            Teleporter.createRTP(player, player.getWorld());
            return;
        }

        Teleporter.createRTP(player, Bukkit.getWorld(AsyncRTP.getAsyncRTP().getConfig().getString("RespawnRTP.RespawnWorld")));
    }
}
