package dev.daerk.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.daerk.KiwiBlack;
import dev.daerk.model.PlayerData;

import org.bukkit.configuration.file.FileConfiguration;


public class PlayersConfigManager extends DataFolderManager{

    public PlayersConfigManager(KiwiBlack plugin, String folderName){
        super(plugin, folderName);
    }


    @Override
    public void loadConfigs(){
        Map<UUID, PlayerData> players = new HashMap<>();
        for(CustomConfig customConfig : configFiles){
            FileConfiguration config = customConfig.getConfig();

            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml",""));
            String name = config.getString("Name");
            int BlackCoins = config.getInt("BlackCoins");

            PlayerData playerData = new PlayerData(uuid,name,BlackCoins);
            players.put(uuid,playerData);
        }

        plugin.getPlayerDataManager().setPlayers(players);
    }

    @Override
    public void saveConfigs(){
        Map<UUID, PlayerData> players = plugin.getPlayerDataManager().getPlayers();
        for(Map.Entry<UUID,PlayerData> entry : players.entrySet()){
            PlayerData playerData = entry.getValue();
            String pathName = playerData.getUuid().toString()+".yml";

            CustomConfig customConfig = getConfigFile(pathName);
            if(customConfig == null){
                //Se crea 
                customConfig = registerConfigFile(pathName);
            }

            FileConfiguration config = customConfig.getConfig();
            config.set("Name", playerData.getName());
            config.set("BlackCoins", playerData.getBlackCoins());
        }

        saveConfigFiles();
    }
}
