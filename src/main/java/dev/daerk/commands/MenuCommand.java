package dev.daerk.commands;

import dev.daerk.KiwiBlack;
import dev.daerk.model.InventoryPlayer;
import dev.daerk.tools.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MenuCommand implements CommandExecutor {

    private KiwiBlack plugin;

    public MenuCommand(KiwiBlack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        // /menu

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cSolo los jugadores pueden ejecutar este comando."));
            return true;
        }

        Player player = (Player)sender;
        plugin.getMenuInventoryManager().openMainInventory(new InventoryPlayer(player));

        return true;
    }
}
