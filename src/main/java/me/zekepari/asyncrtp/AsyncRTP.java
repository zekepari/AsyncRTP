package me.zekepari.asyncrtp;

import me.zekepari.asyncrtp.Commands.Wild;
import me.zekepari.asyncrtp.Events.PlayerJoin;
import me.zekepari.asyncrtp.Events.PlayerRespawn;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public final class AsyncRTP extends JavaPlugin {

    private static AsyncRTP asyncRTP;

    public static HashSet<Material> safeBlocks = new HashSet<>();
    public static HashSet<World> disabledWorlds = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        asyncRTP = this;

        for (String world : getConfig().getStringList("CommandRTP.DisabledWorlds")) {
            disabledWorlds.add(Bukkit.getWorld(world));
        }

        for (String block : getConfig().getStringList("SafeBlocks.Blocks")) {
            safeBlocks.add(Material.matchMaterial(block));
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (getConfig().getBoolean("CommandRTP.Enabled")) {
            getCommand("wild").setExecutor(new Wild());
        }

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AsyncRTP getAsyncRTP() {
        return asyncRTP;
    }
}
