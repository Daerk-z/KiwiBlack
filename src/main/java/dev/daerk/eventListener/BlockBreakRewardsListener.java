package dev.daerk.eventListener;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import dev.daerk.KiwiBlack;
import dev.daerk.tools.MessageColors;

/*
 * Handles the logic behind Block Break Rewards
 * 
 * Allows configuring any block type to duplicate the given rewards
 */
public class BlockBreakRewardsListener implements Listener {

    KiwiBlack plugin;

    public BlockBreakRewardsListener(KiwiBlack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Abort if module is not enabled
        if (!plugin.getFileConfigManager().getIsBlockBreakRewardsEnabled())
            return;

        // Abort if player has no permission
        //if (!event.getPlayer().hasPermission("kiwiblack.block.break"))
        //    return;
            
        // Abort if player is mining silk touch to prevent infinite dupes
        if (event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH))
            return;

        // Check if block matches configured rewards
        if (!plugin.getFileConfigManager().getIsBlockBreakRewardsBlockAllowed(event.getBlock().getType().name()))
            return;

        // Evaluate random chance
        if (plugin.getFileConfigManager().getBlockBreakRewardsBlockChance(event.getBlock().getType().name()) < ThreadLocalRandom.current().nextDouble(100))
            return;

        int dropsReceived = 0;
        String dropName = "";

        // Give the extra drops to the player
        for (ItemStack blockDrop : event.getBlock().getDrops()) {
            int dropsToGive = (int) Math.ceil(blockDrop.getAmount() * plugin.getFileConfigManager().getBlockBreakRewardBlockMultiplier(event.getBlock().getType().name()));
            blockDrop.setAmount(dropsToGive);
            Map<Integer, ItemStack> outOfSpaceItems = event.getPlayer().getInventory().addItem(blockDrop);

            // If no inventory space is available, drop them
            if (outOfSpaceItems.size() > 0) {
                for (ItemStack leftover : outOfSpaceItems.values()) {
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), leftover);
                }
            }

            dropName = blockDrop.getType().name();
            dropsReceived += dropsToGive;

        }

        event.getPlayer().sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aHas encontrado &e" + dropsReceived + " " + dropName + " mas"));
        plugin.getServer().broadcastMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aEl jugador &e" + event.getPlayer().getName() + "&a ha encontrado &ex"+ dropsReceived +" "+ dropName +" extra."));

    }

}
