package org.sparnet.twitchstatus.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.sparnet.twitchstatus.TwitchStatus;
import org.sparnet.twitchstatus.model.LivePlayer;

import java.util.List;

public class TabListener implements Listener {

    private final TwitchStatus plugin;

    public TabListener(TwitchStatus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        List<String> completions = event.getCompletions();

        // Itera sui giocatori onlinerima
        for (Player player : Bukkit.getOnlinePlayers()) {
            LivePlayer livePlayer = plugin.getLivePlayer(player);
            if (livePlayer != null && livePlayer.isLive()) {
                completions.add(ChatColor.GREEN + "[Live] " + player.getName());
            }
        }
    }
}
