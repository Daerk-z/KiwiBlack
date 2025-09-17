package dev.daerk.commands;

import dev.daerk.KiwiBlack;
import dev.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FlyCommand implements CommandExecutor {

    private KiwiBlack plugin;

    public FlyCommand(KiwiBlack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        // /fly <jugador>(opcional)

        if (!sender.hasPermission("KiwiBlack.commands.fly")) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cNo tienes permiso para ejecutar este comando."));
            return true;
        }

        Player player = null;
        if (args.length == 0) {
            if (sender instanceof Player) {
                player = (Player) sender;
                // Bien

            } else {
                // Mal
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar &e/fly."));
                return true;
            }
        } else {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cEl jugador &e " + args[0] + " &cno está conectado."));
                return true;
            }
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cModo de vuelo desactivado."));
            if (!player.equals(sender)) {
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cModo de desactivado para " + player.getName() + "&a."));
            }
        } else {
            player.setAllowFlight(true);
            player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aModo de vuelo activado."));
            if (!player.equals(sender)) {
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aModo de activado para " + player.getName() + "&a."));
            }
        }
        return true;
    }
}
