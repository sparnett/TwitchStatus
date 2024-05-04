package org.sparnet.twitchstatus.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.sparnet.twitchstatus.TwitchStatus;
import org.sparnet.twitchstatus.model.LivePlayer;

public class TwitchStatusPlaceholder extends PlaceholderExpansion {

    private final TwitchStatus plugin;

    public TwitchStatusPlaceholder(TwitchStatus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "twitchstatus";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) {
            return "";
        }

        // Controllo se il placeholder richiesto è quello dello stato live
        if (identifier.equals("live_status")) {
            // Ottieni lo stato live del giocatore
            LivePlayer livePlayer = plugin.getLivePlayer(player);
            if (livePlayer != null && livePlayer.isLive()) {
                return ChatColor.LIGHT_PURPLE + "[Live]";
            } else {
                return "";
            }
        }

        return null;
    }
}
