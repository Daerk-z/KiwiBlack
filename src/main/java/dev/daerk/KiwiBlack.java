package dev.daerk;

import dev.daerk.commands.*;
import dev.daerk.config.FileConfigManager;
import dev.daerk.config.PlayersConfigManager;
import dev.daerk.eventListener.BlockBreakRewardsListener;
import dev.daerk.eventListener.InventoryListener;
import dev.daerk.eventListener.PlayerListener;
import dev.daerk.managers.MenuInventoryManager;
import dev.daerk.managers.PlayerDataManager;
import dev.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class KiwiBlack extends JavaPlugin {

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    private String version = getDescription().getVersion();
    private FileConfigManager fileConfigManager;
    private PlayersConfigManager playersConfigManager;
    private MenuInventoryManager menuInventoryManager;
    private PlayerDataManager playerDataManager;

    public void onEnable() {

        fileConfigManager = new FileConfigManager(this);
        menuInventoryManager = new MenuInventoryManager();
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this, "players");

        this.prefix = fileConfigManager.getPrefix() +"&f";

        registerCommands();
        registerEvents();


        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|            &a&lKIWI                 |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|              &f&lBLACK              &a|"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|          Habilitado             |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|                                 |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|        &bVersion:&e"+ version +"            &a|"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
    }


    public void onDisable() {

        playersConfigManager.saveConfigs();

        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|            &a&lKIWI                 |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|              &f&lBLACK              &a|"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|                                 |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|           &cDesabilitado          &a|"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a|                                 |"));
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&e[&a&lKiwi&f&lBlack&e] &a+=================================+"));
    }


    public void registerCommands() {
        // Crear una instancia del comando principal
        MainCommandKB mainCommandKB = new MainCommandKB(this);

        // Obtener el comando 'kb' y configurar tanto el ejecutor como el completador
        PluginCommand kbCommand = this.getCommand("kb");
        if (kbCommand != null) {
            kbCommand.setExecutor(mainCommandKB);
            kbCommand.setTabCompleter(mainCommandKB);
        }

        // El resto de tus registros de comandos se mantienen igual
        this.getCommand("fly").setExecutor(new FlyCommand(this));
        this.getCommand("xp").setExecutor(new XpCommand(this));
        this.getCommand("item").setExecutor(new ItemCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));
        this.getCommand("tpt").setExecutor(new TptCommand(this));
        this.getCommand("bcoins").setExecutor(new BcoinsCommand(this));
    }
    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakRewardsListener(this), this);
    }

    public FileConfigManager getFileConfigManager(){
     return fileConfigManager;
    }

    public MenuInventoryManager getMenuInventoryManager() {
        return menuInventoryManager;
    }

    public PlayersConfigManager getPlayersConfigManager(){
        return playersConfigManager;
    }

    public PlayerDataManager getPlayerDataManager(){
        return playerDataManager;
    }
}
