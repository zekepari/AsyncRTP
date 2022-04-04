package me.zekepari.asyncrtp;

import me.zekepari.asyncrtp.Commands.Wild;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AsyncRTP extends JavaPlugin {

    private static AsyncRTP asyncRTP;

    @Override
    public void onEnable() {
        // Plugin startup logic
        asyncRTP = this;

        for (String world : getConfig().getStringList("DisabledWorlds")) {
            Wild.disabledWorlds.add(Bukkit.getWorld(world));
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (getConfig().getBoolean("WildCommand.Enabled")) {
            getCommand("wild").setExecutor(new Wild());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AsyncRTP getAsyncRTP() {
        return asyncRTP;
    }
}
