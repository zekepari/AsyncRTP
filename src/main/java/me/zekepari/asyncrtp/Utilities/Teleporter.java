package me.zekepari.asyncrtp.Utilities;

import me.zekepari.asyncrtp.AsyncRTP;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class Teleporter {

    public static void createRTP(Player player, World world) {
        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("SafeBlocks.Enabled") && AsyncRTP.safeBlocks.size() > 0) {
            SafeLocator.findSafeLocation(world).thenAccept(location -> {
                Location finalLocation = location.add(0, 1, 0);
                Bukkit.getScheduler().runTask(AsyncRTP.getAsyncRTP(), () -> issueTeleport(player, finalLocation));
            });
        } else {
            int x = new Random().nextInt(AsyncRTP.getAsyncRTP().getConfig().getInt("Range.X")) * (new Random().nextBoolean() ? -1 : 1);
            int z = new Random().nextInt(AsyncRTP.getAsyncRTP().getConfig().getInt("Range.Z")) * (new Random().nextBoolean() ? -1 : 1);
            issueTeleport(player, world.getHighestBlockAt(x, z).getLocation().add(0, 1, 0));
        }
    }

    private static void issueTeleport(Player player, Location location) {
        TagResolver.Single x = Placeholder.parsed("x", Integer.toString(location.getBlockX()));
        TagResolver.Single y = Placeholder.parsed("y", Integer.toString(location.getBlockY()));
        TagResolver.Single z = Placeholder.parsed("z", Integer.toString(location.getBlockZ()));
        TagResolver placeholders = TagResolver.resolver(x, y, z);

        MessageService.sendMessage(player, AsyncRTP.getAsyncRTP().getConfig().getString("Messages.Random"), placeholders);

        player.teleportAsync(location);

        if (AsyncRTP.getAsyncRTP().getConfig().getBoolean("TeleportSound.Enabled")) {
            int Volume = AsyncRTP.getAsyncRTP().getConfig().getInt("TeleportSound.Volume");
            int Pitch = AsyncRTP.getAsyncRTP().getConfig().getInt("TeleportSound.Pitch");
            String TeleportSound = AsyncRTP.getAsyncRTP().getConfig().getString("TeleportSound.Sound");

            player.playSound(location, Sound.valueOf(TeleportSound), Volume, Pitch);
        }
    }
}
