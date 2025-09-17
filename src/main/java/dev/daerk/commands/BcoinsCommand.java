package dev.daerk.commands;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.daerk.KiwiBlack;
import dev.daerk.managers.PlayerDataManager;
import dev.daerk.tools.MessageColors;

public class BcoinsCommand implements CommandExecutor{

    private KiwiBlack plugin;

    
    public BcoinsCommand(KiwiBlack plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        // /BlackCoins (opcional)<nombre>

        if(!(sender instanceof Player)){
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cEste comando solo puede ser ejecutado por jugadores."));
            return true;
        }
        Player player = (Player)sender;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();

        if(args.length == 0){
            // /BlackCoins
            int BlackCoins = playerDataManager.getBlackCoinsByPlayer(player);
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &aTus BlackCoins:&e " +BlackCoins));
        }else{
            // /BlackCoins <nombre>
            int BlackCoins = playerDataManager.getBlackCoinsByName(args[0]);
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &eBlackCoins de " +args[0]+ ":&e " +BlackCoins));
        }


        return true;
    }
}
