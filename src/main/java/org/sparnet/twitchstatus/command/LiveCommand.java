package org.sparnet.twitchstatus.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sparnet.twitchstatus.TwitchStatus;
import org.sparnet.twitchstatus.model.LivePlayer;

public class LiveCommand implements CommandExecutor {

    private final TwitchStatus plugin;

    public LiveCommand(TwitchStatus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Controlla se il sender è un giocatore
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        // Cast del sender a Player
        Player player = (Player) sender;

        // Verifica i permessi del giocatore per eseguire il comando
        if (!player.hasPermission("twitchstatus.live")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        // Ottenimento dello stato live del giocatore
        LivePlayer livePlayer = plugin.getLivePlayer(player);

        // Se lo stato live è null, crea un nuovo LivePlayer
        if (livePlayer == null) {
            livePlayer = new LivePlayer();
            plugin.setLivePlayer(player, livePlayer);
        }

        // Inverti lo stato live del giocatore
        livePlayer.setLive(!livePlayer.isLive());

        // Invia un messaggio al giocatore in base allo stato live attuale
        if (livePlayer.isLive()) {
            player.sendMessage(ChatColor.GREEN + "From now players will see in TAB that you are LIVE!");
        } else {
            player.sendMessage(ChatColor.RED + "Players will now NOT be able to see that you are live in TAB!");
        }

        return true;
    }
}
