package me.zekepari.asyncrtp.Utilities;

import me.zekepari.asyncrtp.AsyncRTP;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Teleporter {
    public static HashSet<Material> safeBlocks = new HashSet<>();

    public static void issueRTP(Player player, World world) {
        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("SafeBlocks.Enabled")) {

        }
        CompletableFuture<Location> newLocation = new CompletableFuture<>();

        Bukkit.getScheduler().runTaskAsynchronously(AsyncRTP.getAsyncRTP(), new Runnable() {
            int attempts = 0;
            @Override
            public void run() {
                if (attempts >= AsyncRTP.getAsyncRTP().getConfig().getInt("SafeBlocks.MaxAttempts")) {
                    newLocation.completeAsync(null);
                    return;
                }
                attempts ++;
                int x = new Random().nextInt(AsyncRTP.getAsyncRTP().getConfig().getInt("Range.X")) * (new Random().nextBoolean() ? -1 : 1);
                int z = new Random().nextInt(AsyncRTP.getAsyncRTP().getConfig().getInt("Range.Z")) * (new Random().nextBoolean() ? -1 : 1);

                world.getChunkAtAsync(new Location(world, x, 0, z)).thenAccept(chunk -> {
                    Block highestBlock = world.getHighestBlockAt(x, z);
                    if (!safeBlocks.contains(highestBlock.getType())) {
                        Bukkit.getScheduler().runTaskAsynchronously(AsyncRTP.getAsyncRTP(), this);
                        return;
                    }
                    newLocation.completeAsync(highestBlock::getLocation);
                });
            }
        });

        newLocation.thenAccept(location -> {
            Location finalLocation = location.add(0, 1, 0);
            Bukkit.getScheduler().runTask(AsyncRTP.getAsyncRTP(), () -> {
                HashMap<String, String> placeholders = new HashMap<>();
                placeholders.put("x", Integer.toString(finalLocation.getBlockX()));
                placeholders.put("y", Integer.toString(finalLocation.getBlockY()));
                placeholders.put("z", Integer.toString(finalLocation.getBlockZ()));
                MessageService.sendMessage(player, AsyncRTP.getAsyncRTP().getConfig().getString("Messages.Random"), placeholders);

                player.teleportAsync(finalLocation);

                if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("TeleportSound.Enabled")) {
                    int Volume = AsyncRTP.getAsyncRTP().getConfig().getInt("TeleportSound.Volume");
                    int Pitch = AsyncRTP.getAsyncRTP().getConfig().getInt("TeleportSound.Pitch");
                    String TeleportSound = AsyncRTP.getAsyncRTP().getConfig().getString("TeleportSound.Sound");

                    player.playSound(finalLocation, Sound.valueOf(TeleportSound), Volume, Pitch);
                }
            });
        });
    }
}
