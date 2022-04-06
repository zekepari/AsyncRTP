package me.zekepari.asyncrtp.Utilities;

import me.zekepari.asyncrtp.AsyncRTP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class SafeLocator {

    public static CompletableFuture<Location> findSafeLocation(World world) {
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
                    if (!AsyncRTP.safeBlocks.contains(highestBlock.getType())) {
                        Bukkit.getScheduler().runTaskAsynchronously(AsyncRTP.getAsyncRTP(), this);
                        return;
                    }
                    newLocation.completeAsync(highestBlock::getLocation);
                });
            }
        });

        return newLocation;
    }
}
