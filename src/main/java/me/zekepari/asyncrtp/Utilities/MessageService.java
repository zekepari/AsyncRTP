package me.zekepari.asyncrtp.Utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

public class MessageService {

    public static void sendMessage(Player player, String message) {
        if (message.isEmpty()) { return; }
        Component parsed = MiniMessage.miniMessage().deserialize(message);
        player.sendMessage(parsed);
    }

    public static void sendMessage(Player player, String message, TagResolver placeholders) {
        if (message.isEmpty()) { return; }
        Component parsed = MiniMessage.miniMessage().deserialize(message, placeholders);
        player.sendMessage(parsed);
    }
}
