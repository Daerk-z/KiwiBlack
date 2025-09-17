package dev.daerk.commands;

import dev.daerk.KiwiBlack;
import dev.daerk.tools.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MainCommandKB implements CommandExecutor, TabCompleter {
    private final KiwiBlack plugin;

    public MainCommandKB(KiwiBlack plugin){
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Comandos base
            if (sender instanceof Player) {
                completions.add("bienvenida");
                completions.add("fecha");

                if (sender.hasPermission("KiwiBlack.commands.get")) {
                    completions.add("get");
                }

                if (sender.hasPermission("KiwiBlack.commands.reload")) {
                    completions.add("reload");
                }

                if (sender.hasPermission("kiwiblack.admin.setprefix")) {
                    completions.add("setprefix");
                }
            } else {
                // Opciones para la consola
                completions.add("reload");
                completions.add("setprefix");
            }

            return filterCompletions(completions, args[0]);
        }

        // Subcomandos de 'get'
        if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            if (sender.hasPermission("KiwiBlack.commands.get")) {
                completions.add("autor");
                completions.add("version");
                return filterCompletions(completions, args[1]);
            }
        }

        return completions;
    }

    // Método auxiliar para filtrar las sugerencias según lo que el usuario está escribiendo
    private List<String> filterCompletions(List<String> completions, String partial) {
        return completions.stream()
                .filter(s -> s.toLowerCase().startsWith(partial.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        if (args.length >= 2 && args[0].equalsIgnoreCase("setprefix")) {
            if (!sender.hasPermission("kiwiblack.admin.setprefix")) {
                sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &c No tienes permiso para ejecutar este comando."));
                return true;
            }

            // Unir todos los argumentos después de "setprefix" para permitir espacios en el prefix
            StringBuilder newPrefix = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                newPrefix.append(args[i]).append(" ");
            }

            // Actualizar el prefix en el config.yml
            plugin.getConfig().set("config.prefix", newPrefix.toString().trim());
            plugin.saveConfig();

            // Recargar la configuración
            plugin.getFileConfigManager().reloadConfig();

            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &a Prefix actualizado correctamente."));
            return true;
        }


        if(!(sender instanceof Player)){
            //Consola
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &cEste comando lo debe ejecutar un jugador."));
            return true;
        }

        //Jugador
        Player player = (Player) sender;

        // /kb args[0] args[1] args[2]

        if(args.length >= 1){
                // /kb bienvenida
            if(args[0].equalsIgnoreCase("bienvenida")){
                sender.sendMessage(MessageColors.coloredMessage("&e¡Bienvenido &a"+player.getName()+"&e!"));

                // /kb fecha
            }else if(args[0].equalsIgnoreCase("fecha")){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = dateFormat.format(new Date());
                sender.sendMessage(MessageColors.coloredMessage("&eFecha y hora actual:"+date));

                // /kb get <autor/version>
                // Sub-comando con sub-comando (XD)
            }else if(args[0].equalsIgnoreCase("get")){
                subCommandGet(sender, args);


            }else if(args[0].equalsIgnoreCase("reload")){
                // /kb reload
                subCommandReload(sender);
            }
        }else{
            // Mensaje que da al jugador que ejecute el comando sin ningún argumento válido.
            help(sender);
        }
        return true;
    }

    public void help(CommandSender sender){
        sender.sendMessage(MessageColors.coloredMessage("&f&l----------COMANDOS &a&lKIWI&f&lBLACK----------"));
        sender.sendMessage(MessageColors.coloredMessage("&f"));
        sender.sendMessage(MessageColors.coloredMessage("&e /kb bienvenida"));
        sender.sendMessage(MessageColors.coloredMessage("&e /kb fecha"));
        sender.sendMessage(MessageColors.coloredMessage("&e /kb get <arg>"));
        sender.sendMessage(MessageColors.coloredMessage("&f"));
        sender.sendMessage(MessageColors.coloredMessage("&f&l----------COMANDOS &a&lKIWI&f&lBLACK----------"));
    }

    public void subCommandGet(CommandSender sender, String[] args){

        if(!sender.hasPermission("KiwiBlack.commands.get")){
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &c No tienes permiso para ejecutar este comando."));
            return;
        }

        // /kb get <none>
        if(args.length == 1){
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &c Debes usar &e/kb get <autor/version>&c."));
            return;
        }

        // /kb get autor
        if (args[1].equalsIgnoreCase("autor")){
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &a Los autores del plugin son: "+plugin.getDescription().getAuthors()));

            // /kb get version
        }else if (args[1].equalsIgnoreCase("version")){
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() +" &a La versión del plugin es: "+plugin.getDescription().getVersion()));

            // /kb get <none>
        }else{
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() +" &c Debes usar &e/kb get <autor/version>&c."));
        }
    }

    public void subCommandReload(CommandSender sender) {

        if (!sender.hasPermission("KiwiBlack.commands.relaod")) {
            sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &c No tienes permiso para ejecutar este comando."));
            return;
        }
        plugin.getFileConfigManager().reloadConfig();
        sender.sendMessage(MessageColors.coloredMessage(plugin.getPrefix() + " &a Configuración recargada correctamente."));
    }
}
