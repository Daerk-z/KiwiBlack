package kb.daerk;

import kb.daerk.commands.*;
import kb.daerk.config.FileConfigManager;
import kb.daerk.eventListener.InventoryListener;
import kb.daerk.eventListener.PlayerListener;
import kb.daerk.managers.MenuInventoryManager;
import kb.daerk.tools.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class KiwiBlackPP extends JavaPlugin {

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    private String version = getDescription().getVersion();
    private FileConfigManager fileConfigManager;
    private MenuInventoryManager menuInventoryManager;

    public void onEnable() {

        fileConfigManager = new FileConfigManager(this);
        this.prefix = fileConfigManager.getPrefix() +"&f";
        registerCommands();
        registerEvents();
        menuInventoryManager = new MenuInventoryManager();


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
    }
    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    public FileConfigManager getFileConfigManager(){
     return fileConfigManager;
    }

    public MenuInventoryManager getMenuInventoryManager() {
        return menuInventoryManager;
    }
}
