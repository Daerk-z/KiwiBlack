package kb.daerk.commands;

import kb.daerk.KiwiBlackPP;
import kb.daerk.tools.ItemTools;
import kb.daerk.tools.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {

    private KiwiBlackPP plugin;

    public ItemCommand(KiwiBlackPP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cEste comando solo puede ser ejecutado por jugadores."));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("kiwiblackpp.commands.item")) {
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cNo tienes permiso para ejecutar este comando."));
            return true;
        }

        int amount = 1;
        if (args.length >= 1) {
            try {
                amount = Integer.parseInt(args[0]);
                if (amount <= 0) {
                    player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar una cantidad válida."));
                    return true;
                }
            } catch (NumberFormatException e) {
                player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar una cantidad válida."));
                return true;
            }
        }

        if(player.getInventory().firstEmpty() == -1){
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cTienes el inventario lleno."));
        }else{
            ItemStack item = ItemTools.generateDiamond(amount);
            player.getInventory().addItem(item);
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aHas recibido &e" + amount + " &ade &e" + item.getType().name() + "&a."));
        }
        return true;
    }
}
