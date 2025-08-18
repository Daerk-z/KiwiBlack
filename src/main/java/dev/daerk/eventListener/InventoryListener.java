package dev.daerk.eventListener;

import dev.daerk.KiwiBlackPP;
import dev.daerk.Model.InventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    private KiwiBlackPP plugin;

    public InventoryListener(KiwiBlackPP plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        InventoryPlayer inventoryPlayer = plugin.getMenuInventoryManager().getInventoryPlayer(player);
        if(inventoryPlayer != null){
            // El jugador está dentro de un inventario.

            event.setCancelled(true);
            //player.sendMessage("cancelado...");
            if(event.getCurrentItem() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){
                plugin.getMenuInventoryManager().inventoryClick(inventoryPlayer, event.getSlot(),event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        plugin.getMenuInventoryManager().removePlayer(player);
        //player.sendMessage("saliendo...");
    }
}
