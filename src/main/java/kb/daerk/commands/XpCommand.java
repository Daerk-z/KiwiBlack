package kb.daerk.commands;

import kb.daerk.KiwiBlackPP;
import kb.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpCommand implements CommandExecutor {

    private KiwiBlackPP plugin;

    public XpCommand(KiwiBlackPP plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        // /xp <cantidad> <jugador>(opcional)

        if (!sender.hasPermission("kiwiblackpp.commands.xp")) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cNo tienes permiso para ejecutar este comando."));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar &e/xp <cantidad> <jugador>(opcional)."));
            return true;
        }
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(args[0]);
            if (cantidad <= 0) {
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar una cantidad válida."));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar una cantidad válida."));
        }

        Player player = null;
        if (args.length == 1) {
            if (sender instanceof Player) {
                player = (Player)sender;
                // Bien

            } else {
                // Mal
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cDebes usar &e/xp <cantidad> <jugador>(opcional)."));
                return true;
            }
        }else{
            player = Bukkit.getPlayer(args[1]);
            if(player == null){
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cEl jugador &e"+args[1]+" &cno está conectado."));
                return true;
            }
        }

        player.giveExpLevels(cantidad);
        player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &e Has recibido &e"+cantidad+" &eniveles de experiencia."));
        if (!player.equals(sender)) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &e El jugador &e" + args[1] + " &eha recibido " + cantidad + " niveles de experiencia."));
        }
        return true;
    }
}
