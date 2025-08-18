package dev.daerk.commands;

import dev.daerk.KiwiBlackPP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dev.daerk.tools.MessageColors;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TptCommand implements CommandExecutor {

    private KiwiBlackPP plugin;

    public TptCommand(KiwiBlackPP plugin) {
        this.plugin = plugin;
    }

    // Mapa para almacenar el tiempo de finalización del cooldown por jugador
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    // Duración del cooldown en segundos (puedes cambiarlo)
    private static final int COOLDOWN_SECONDS = 30;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Solo se permite que lo ejecute un jugador
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo los jugadores pueden ejecutar este comando.");
            return true;
        }
        Player player = (Player) sender;


        // VERIFICAR EL COOLDOWN

        // Revisamos si el jugador ya tiene un registro en el mapa de cooldowns
        if (cooldowns.containsKey(player.getUniqueId())) {
            long tiempoRestante = cooldowns.get(player.getUniqueId()) - System.currentTimeMillis();
            // Si todavía queda tiempo por esperar
            if (tiempoRestante > 0) {
                // Convertimos a segundos
                long segundosRestantes = tiempoRestante / 1000;
                player.sendMessage(MessageColors.coloredMessage("&cAún debes esperar " + segundosRestantes + " segundos para volver a usar este comando."));
                return true;
            }
        }


        // PROCESAR EL COMANDO /TPT

        // Verificar que se haya indicado el nombre de un jugador
        if (args.length != 1) {
            player.sendMessage(MessageColors.coloredMessage("&eUso: &a/tp <jugador>"));
            return true;
        }

        // Obtener al jugador destino de forma exacta
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(MessageColors.coloredMessage("&cEl jugador especificado no está conectado."));
            return true;
        }

        if (target == player) {
            player.sendMessage(MessageColors.coloredMessage("&cNo te puedes teletransportar a ti mismo, bastardito. <3"));
            return true;
        }

        // Definir la cantidad requerida de diamantes
        //int requiredSnowball = 10;

        // Verificar si el jugador tiene al menos 10 diamantes en su inventario
        //if (!player.getInventory().containsAtLeast(new ItemStack(Material.SNOWBALL), requiredSnowball)) {
        //    player.sendMessage(MessageColors.coloredMessage("&cNo tienes suficientes bolas de nieve para usar este comando."));
        //    return true;
        //}

        // Retirar 10 diamantes del inventario del jugador
        //removeItems(player, Material.SNOWBALL, requiredSnowball);

        // Teletransportar al jugador al destino
        player.teleport(target);
        player.sendMessage(MessageColors.coloredMessage(plugin.getPrefix()+"&e Te has teletransportado a &a" + target.getName()));
                //+ "&e. Se han retirado &b" + requiredSnowball + "&e bolas de nieve de tu inventario."));

        // Aviso global (broadcast)
        Bukkit.broadcastMessage(MessageColors.coloredMessage("&b&m------------------------------"));
        Bukkit.broadcastMessage(MessageColors.coloredMessage("&eEl jugador &a"+ player.getName() + "&e se ha"));
        Bukkit.broadcastMessage(MessageColors.coloredMessage("&eteletransportado a &a" + target.getName() + "&e."));
        Bukkit.broadcastMessage(MessageColors.coloredMessage("&ejiji"));
        Bukkit.broadcastMessage(MessageColors.coloredMessage("&b&m------------------------------"));


        // ESTABLECER EL NUEVO COOLDOWN
        long tiempoDesbloqueo = System.currentTimeMillis() + (COOLDOWN_SECONDS * 1000L);
        cooldowns.put(player.getUniqueId(), tiempoDesbloqueo);

        return true;
    }

    /**
     * Método para remover una cantidad específica de items de un tipo dado
     * del inventario de un jugador, sin eliminar todos los ítems coincidentes
     * en cada slot.
     *
     * Esto por un error que me dio antes, jiejiejiejiejie
     */
    //private void removeItems(Player player, Material material, int amount) {
    //    int remaining = amount;
    //    ItemStack[] contents = player.getInventory().getContents();
    //
    //    for (int i = 0; i < contents.length; i++) {
    //        ItemStack item = contents[i];
    //        if (item != null && item.getType() == material) {
    //            int stackAmount = item.getAmount();
    //
    //            if (stackAmount <= remaining) {
    //                remaining -= stackAmount;
    //                player.getInventory().setItem(i, null); // Limpia el slot
    //            } else {
    //                item.setAmount(stackAmount - remaining);
    //                player.getInventory().setItem(i, item);
    //                remaining = 0;
    //            }
    //
    //            if (remaining <= 0) {
    //                break;
    //            }
    //        }
    //    }
    //}
}
