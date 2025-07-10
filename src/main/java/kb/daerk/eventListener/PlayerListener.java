package kb.daerk.eventListener;

import kb.daerk.KiwiBlackPP;
import kb.daerk.config.FileConfigManager;
import kb.daerk.tools.ItemTools;
import kb.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;
import java.util.Set;


public class PlayerListener implements Listener {

    private KiwiBlackPP plugin;

    public PlayerListener(KiwiBlackPP plugin) {
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


        /**
         * Eventos que ocurren al romper un bloque.
         */
        @EventHandler
        public void onBlockBreak (BlockBreakEvent event){
            Player player = event.getPlayer();

            Set<Material> hierrosValidos = Set.of(Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE);
            Set<Material> orosValidos = Set.of(Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE);
            Set<Material> diamantesValidos = Set.of(Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE);
            Set<Material> esmeraldasValidas = Set.of(Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE);
            Set<Material> carbonValido = Set.of(Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE);
            Set<Material> lapizValida = Set.of(Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE);



            if (player.getWorld().getName().equals("world") && player.hasPermission("kiwiblack.block.break")) {
                event.setCancelled(true);
                player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ plugin.getFileConfigManager().getPreventBlockBreakMessage()));
            }

            // DIAMANTE
            Block block = event.getBlock();
            if(diamantesValidos.contains(block.getType())){
                int num = new Random().nextInt(10);
                if(num == 0){
                    ItemStack item = ItemTools.generateDiamond(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aHas encontrado &e1 diamante extra &aal minar una mena de diamante."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 diamante&a, minando una mena de diamante."));
                }
            }

            // ORO
            if(orosValidos.contains(block.getType())){
                int num = new Random().nextInt(10);
                if(num == 0){
                    ItemStack item = ItemTools.generateRawGold(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aHas encontrado &e1 oro crudo extra &aal minar una mena de oro."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 oro crudo&a, minando una mena de oro."));
                }
            }

            // HIERRO
            if(hierrosValidos.contains(block.getType())){
                int num = new Random().nextInt(10);
                if(num == 0){
                    ItemStack item = ItemTools.generateRawIron(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aHas encontrado &e1 hierro crudo extra &aal minar una mena de hierro."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 hierro crudo&a, minando una mena de hierro."));
                }
            }

            // ESMERALDA
            if(esmeraldasValidas.contains(block.getType())){
                int num = new Random().nextInt(10);
                if(num >= 4){
                    ItemStack item = ItemTools.generatePotion(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aHas encontrado &e1 poción de rescate &aal minar una mena de emeralda."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix()+ " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 poción de rescate&a, minando una mena de esmeralda."));
                }
            }

            // CARBON
            if(carbonValido.contains(block.getType())) {
                int num = new Random().nextInt(10);
                if (num == 0) {
                    ItemStack item = ItemTools.generateCoal(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aHas encontrado &e1 carbon extra &aal minar una mena de carbon."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 carbon&a, minando una mena de carbon."));
                }
            }

            // LAPIS
            if(lapizValida.contains(block.getType())) {
                int num = new Random().nextInt(10);
                if (num == 0) {
                    ItemStack item = ItemTools.generateLapis(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aHas encontrado &e1 lapis lazuli &aal minar una mena de lapis lazuli."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aEl jugador &e" + player.getName() + "&a ha encontrado &ex1 lapis lazuli&a, minando una mena de lapis lazuli."));
                }
            }

            // ANCIEN DEBRIS
            if(block.getType().equals(Material.ANCIENT_DEBRIS)) {
                int num = new Random().nextInt(10);
                if (num >= 4) {
                    ItemStack item = ItemTools.generateLapis(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &dHas encontrado &e1 escombro ancestral &dal minar un escombro ancestral."));
                    Bukkit.broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &dEl jugador &e" + player.getName() + "&d ha encontrado &ex1 escombro ancestral&d, minando un escombro ancestral."));
                }
            }
        }
    }
