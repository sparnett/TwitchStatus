package org.sparnet.twitchstatus.util;

import org.bukkit.entity.Player;
import org.sparnet.twitchstatus.TwitchStatus;
import org.sparnet.twitchstatus.model.LivePlayer;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final TwitchStatus plugin;
    private Map<Player, LivePlayer> livePlayers = new HashMap<>();

    public ConfigManager(TwitchStatus plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        // Implementa il caricamento della configurazione se necessario
    }

    public void saveConfig() {
        // Implementa il salvataggio della configurazione se necessario
    }

    public LivePlayer getLivePlayer(Player player) {
        return livePlayers.get(player);
    }

    public void setLivePlayer(Player player, LivePlayer livePlayer) {
        livePlayers.put(player, livePlayer);
    }
}
