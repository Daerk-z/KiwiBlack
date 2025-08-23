package dev.daerk.managers;

import dev.daerk.Model.InventoryPlayer;
import dev.daerk.Model.InventorySection;
import dev.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class MenuInventoryManager {
    private ArrayList<InventoryPlayer> players;

    public MenuInventoryManager(){
        this.players = new ArrayList<>();
    }
/*
*  Detecta si el jugador tiene una instancia de la clase InventoryPlayer.
 */
    public InventoryPlayer getInventoryPlayer(Player player){
        for(InventoryPlayer inventoryPlayer : players){
            if(inventoryPlayer.getPlayer().equals(player)){
                return inventoryPlayer;
            }

        }
        return null;
    }

    public void removePlayer(Player player){
        players.removeIf(inventoryPlayer -> inventoryPlayer.getPlayer().equals(player));
    }

    public void openMainInventory(InventoryPlayer inventoryPlayer){
        inventoryPlayer.setSection(InventorySection.MENU_MAIN);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageColors.coloredMessage("&e&lMenú Principal"));


        // Items decorativos.

        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for(int i=45; i<54; i++){
            inv.setItem(i, item);
        }

        // Items informativos.

        item = new ItemStack(Material.BOOK);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&bInformación de usuario"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&eNivel: &a"+player.getLevel()));
        lore.add(MessageColors.coloredMessage("&eExperiencia: &a"+player.getTotalExperience()));
        lore.add(MessageColors.coloredMessage("&ePing: &a"+player.getPing()));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);


        // Acceder a sub-inventario.

        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&bEfectos de poción"));
        lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&7Clickea aquí para acceder a &eEfectos de poción&7."));
        lore.add(MessageColors.coloredMessage("&cSolo para VIPS."));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inv.setItem(24, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openEffectInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_EFFECTS);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MessageColors.coloredMessage("&e&lMenú de efectos"));


        // Items decorativos.

        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for (int i = 19; i < 26; i++) {
            inv.setItem(i, item);
        }

        // Items de poción

        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&eEfecto de salto"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&7Click para recibir lo siguiente:"));
        lore.add(MessageColors.coloredMessage(""));
        lore.add(MessageColors.coloredMessage("&eSalto II &7(10 segundos)"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inv.setItem(10, item);


        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&ePoción multi-efectos"));

        lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&7Click para recibir lo siguiente:"));
        lore.add(MessageColors.coloredMessage(""));
        lore.add(MessageColors.coloredMessage("&eSalto II &7(10 segundos)"));
        lore.add(MessageColors.coloredMessage("&eFuerza III &7(10 segundos)"));
        lore.add(MessageColors.coloredMessage("&eRegeneración V &7(10 segundos)"));
        lore.add(MessageColors.coloredMessage("&eVisión nocturna I &7(10 segundos)"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inv.setItem(15, item);

        // Atrás
        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&bAtrás"));
        item.setItemMeta(meta);
        inv.setItem(18, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void inventoryClick(InventoryPlayer inventoryPlayer, int slot, ClickType clickType){
        Player player = inventoryPlayer.getPlayer();
        InventorySection section = inventoryPlayer.getSection();
        if(section.equals(InventorySection.MENU_MAIN)){
            if(slot == 24){
                if(player.hasPermission("kiwiblackpp.vip") && clickType.equals(ClickType.LEFT)){
                    player.sendMessage(MessageColors.coloredMessage("&cEste comando solo puede ser ejecutado por jugadores VIP."));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2.0F);
                    return;
                }
                openEffectInventory(inventoryPlayer);
            }

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2.0F);
            player.stopSound(Sound.MUSIC_DRAGON);
        }else if(section.equals(InventorySection.MENU_EFFECTS)){
            if(slot == 18){
                openMainInventory(inventoryPlayer);
            }else if(slot == 10){
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,200, 1,false,false,true));
                player.sendMessage(MessageColors.coloredMessage("&aHas recibido el efecto de &eSalto II &a."));
            } else if (slot == 15) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,200, 1,false,false,true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,200, 2,false,false,true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,200, 4,false,false,true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,200, 0,false,false,true));
                player.sendMessage(MessageColors.coloredMessage("&aHas recibido los siguientes efectos:"));
                player.sendMessage(MessageColors.coloredMessage("&eFuerza III &7(10 segundos)"));
                player.sendMessage(MessageColors.coloredMessage("&eSalto II &7(10 segundos)"));
                player.sendMessage(MessageColors.coloredMessage("&eRegeneración V &7(10 segundos)"));
                player.sendMessage(MessageColors.coloredMessage("&eVisión nocturna I &7(10 segundos)"));
            }
        }
    }
}
