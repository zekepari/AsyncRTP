package me.zekepari.asyncrtp.Commands;

import me.zekepari.asyncrtp.AsyncRTP;
import me.zekepari.asyncrtp.Utilities.MessageService;
import me.zekepari.asyncrtp.Utilities.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.HashMap;

public class Wild implements CommandExecutor {

    private final HashMap<Player, Long> onCooldown = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (((Player) sender).getPlayer());

            if (AsyncRTP.disabledWorlds.contains(player.getWorld())) {
                MessageService.sendMessage(player, AsyncRTP.getAsyncRTP().getConfig().getString("CommandRTP.Messages.World"));
                return true;
            }

            if (onCooldown.containsKey(player)) {
                MessageService.sendMessage(player, AsyncRTP.getAsyncRTP().getConfig().getString("CommandRTP.Messages.Cooldown"));
                return true;
            }

            onCooldown.put(player, Instant.now().getEpochSecond());
            Teleporter.issueRTP(player, player.getWorld());

            Bukkit.getScheduler().runTaskLater(AsyncRTP.getAsyncRTP(), () -> onCooldown.remove(player), AsyncRTP.getAsyncRTP().getConfig().getInt("CommandRTP.Cooldown") * 20L);
        }
        return true;
    }
}
