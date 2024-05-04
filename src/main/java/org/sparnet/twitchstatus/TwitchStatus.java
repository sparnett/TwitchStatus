package org.sparnet.twitchstatus;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.sparnet.twitchstatus.command.LiveCommand;
import org.sparnet.twitchstatus.listener.TabListener;
import org.sparnet.twitchstatus.model.LivePlayer;
import org.sparnet.twitchstatus.placeholder.TwitchStatusPlaceholder;
import org.sparnet.twitchstatus.util.ConfigManager;

import java.io.File;

public class TwitchStatus extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Crea il folder se non esiste
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Crea il file config.yml se non esiste
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
            getLogger().info("Il file config.yml è stato creato.");
        }

        // Inizializza il ConfigManager e carica la configurazione
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Registra il comando /live
        getCommand("live").setExecutor(new LiveCommand(this));

        // Registra il listener TabListener
        getServer().getPluginManager().registerEvents(new TabListener(this), this);

        // Verifica se PlaceholderAPI è presente
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            // Registra il placeholder
            new TwitchStatusPlaceholder(this).register();
            getLogger().info("Placeholder registrato con successo!");
        } else {
            getLogger().warning("PlaceholderAPI non trovato! Placeholder non registrato.");
        }

        // Stampa un messaggio di abilitazione nel log
        getLogger().info("TwitchStatus è stato abilitato!");
    }

    @Override
    public void onDisable() {
        // Salva la configurazione quando il plugin viene disabilitato
        configManager.saveConfig();

        // Stampa un messaggio di disabilitazione nel log
        getLogger().info("TwitchStatus è stato disabilitato!");
    }

    // Metodo per ottenere lo stato live del giocatore
    public LivePlayer getLivePlayer(Player player) {
        return configManager.getLivePlayer(player);
    }

    // Metodo per impostare lo stato live del giocatore
    public void setLivePlayer(Player player, LivePlayer livePlayer) {
        configManager.setLivePlayer(player, livePlayer);
    }
}
