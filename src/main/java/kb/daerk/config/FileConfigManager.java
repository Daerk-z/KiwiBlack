package kb.daerk.config;

import kb.daerk.KiwiBlackPP;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class FileConfigManager {

    private CustomConfig configFile;

    private KiwiBlackPP plugin;

    private String prefix;
    private Boolean isWelcomeMessageEnable;
    private List<String> welcomeMessageMessage;
    private String preventBlockBreakMessage;
    private Boolean isTeleportOnJoinEnable;
    private List<String> teleportInLocation;
    private String blockWordsMessage;

    private Boolean isBlockBreakRewardsEnabled;
    ConfigurationSection blockBreakRewardsBlocksSection;

    public FileConfigManager(KiwiBlackPP plugin) {
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    /**
     * Carga los datos de la configuración siguiendo el PATH correspondiente.
     */
    public void loadConfig() {

        FileConfiguration config = configFile.getConfig();
        prefix = config.getString("config.prefix");
        isWelcomeMessageEnable = config.getBoolean("config.welcome_message.enable");
        welcomeMessageMessage = config.getStringList("config.welcome_message.message");
        preventBlockBreakMessage = config.getString("messages.prevent_block_break");
        isTeleportOnJoinEnable = config.getBoolean("config.teleport_on_join.enable");
        teleportInLocation = config.getStringList("config.teleport_on_join.Location");
        blockWordsMessage = config.getString("messages.block_words_message");

        isBlockBreakRewardsEnabled = config.getBoolean("config.block_break_rewards.enabled");
        blockBreakRewardsBlocksSection = config.getConfigurationSection("config.block_break_rewards.blocks");
    }

    /**
     * Recarga la configuración en caso de haber hecho un cambio.
     */
    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    /**
     * Se encarga de obtener los datos del "public void loadConfig" regresando sus
     * valores asignados.
     */
    public String getPreventBlockBreakMessage() {
        return preventBlockBreakMessage;
    }

    public List<String> getWelcomeMessageMessage() {
        return welcomeMessageMessage;
    }

    public Boolean getIsWelcomeMessageEnable() {
        return isWelcomeMessageEnable;
    }

    public Boolean getIsTeleportOnJoinEnable() {
        return isTeleportOnJoinEnable;
    }

    public List<String> getTeleportInLocation() {
        return teleportInLocation;
    }

    public String getBlockWordsMessage() {
        return blockWordsMessage;
    }

    public String getPrefix() {
        return prefix;
    }

    public Boolean getIsBlockBreakRewardsEnabled() {
        return isBlockBreakRewardsEnabled;
    }

    public Boolean getIsBlockBreakRewardsBlockAllowed(String blockName) {
        return blockBreakRewardsBlocksSection.contains(blockName);
    }

    public double getBlockBreakRewardsBlockChance(String blockName) {
        return blockBreakRewardsBlocksSection.getDouble(blockName + ".chance", 0.0);
    }

    public Integer getBlockBreakRewardBlockMultiplier(String blockName) {
        return blockBreakRewardsBlocksSection.getInt(blockName + ".multiplier", 0);
    }

}
