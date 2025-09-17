package dev.daerk.eventListener;

import dev.daerk.KiwiBlack;
import dev.daerk.config.FileConfigManager;
import dev.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Random;

public class PlayerListener implements Listener {

    private KiwiBlack plugin;

    public PlayerListener(KiwiBlack plugin) {
        this.plugin = plugin;
    }

    /**
     * Eventos que ocurren al escribir en el chat.
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.toLowerCase().contains("esca es hombre")) {
            event.setCancelled(true);
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ plugin.getFileConfigManager().getBlockWordsMessage()));
        }
    }

    /**
     * Eventos que ocurren al unirse al servidor.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerDataManager().updateName(player);

        event.setJoinMessage(MessageColors.coloredMessage("&e[&a+&e] &d" +player.getName()+ "&e se ha unido."));

        FileConfigManager fileConfigManager = plugin.getFileConfigManager();
        if (fileConfigManager.getIsWelcomeMessageEnable()) {
            List<String> message = fileConfigManager.getWelcomeMessageMessage();
            for (String m : message) {
                player.sendMessage(MessageColors.coloredMessage(m.replace("%player%", player.getName())));
            }

            // Verificar si el teletransporte está habilitado
            if (fileConfigManager.getIsTeleportOnJoinEnable()) {
                List<String> locationData = fileConfigManager.getTeleportInLocation();
                Location location = new Location(
                        Bukkit.getWorld(locationData.get(0)),           // Mundo
                        Double.parseDouble(locationData.get(1)),        // x
                        Double.parseDouble(locationData.get(2)),        // y
                        Double.parseDouble(locationData.get(3)),        // z
                        Float.parseFloat(locationData.get(4)),          // Yaw
                        Float.parseFloat(locationData.get(5))           // Pitch
                );
                player.teleport(location);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(MessageColors.coloredMessage("&e[&c-&e] &d" + player.getName() + "&e se ha desconectado."));
    }

    @EventHandler
    public void onEndermanKill(EntityDeathEvent event){
        if(!event.getEntity().getType().equals(EntityType.ENDERMAN)){
            return;
        }

        Player player = event.getEntity().getKiller();
        if(player != null){
            int num = new Random().nextInt(10)+1;
            plugin.getPlayerDataManager().addBlackCoins(player, num);

            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aHas recibido" +num+ "&eBlackCoins"));
        }
    }
}
