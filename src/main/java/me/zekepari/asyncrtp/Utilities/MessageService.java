package me.zekepari.asyncrtp.Utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageService {

    public static void sendMessage(Player player, String message) {
        if (message.isEmpty()) { return; }
        Component component = MiniMessage.get().parse(message);
        player.sendMessage(component);
    }

    public static void sendMessage(Player player, String message, HashMap<String, String> placeholders) {
        if (message.isEmpty()) { return; }
        Component component = MiniMessage.get().parse(message, placeholders);
        player.sendMessage(component);
    }
}
