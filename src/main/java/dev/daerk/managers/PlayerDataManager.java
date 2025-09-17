package dev.daerk.managers;

import dev.daerk.model.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerDataManager {
    private Map<UUID, PlayerData> players;
    private Map<String,UUID> playerNames;

    public PlayerDataManager() {
        players = new HashMap<>();
        playerNames = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for(Map.Entry<UUID,PlayerData> entry : players.entrySet()){
            playerNames.put(entry.getValue().getName(),entry.getKey());
        }
    }

    public void addPlayer(PlayerData p){
        players.put(p.getUuid(),p);
        playerNames.put(p.getName(),p.getUuid());
    }

    public PlayerData getPlayer(Player player,boolean create){
        PlayerData playerData = players.get(player.getUniqueId());
        if(playerData == null && create){
            playerData = new PlayerData(player.getUniqueId(),player.getName(), 0);
            addPlayer(playerData);
        }
        return playerData;
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }


    //
    // Añade "BlackCoins" a un jugador.
    //
    public void addBlackCoins(Player player,int amount){
        PlayerData playerData = getPlayer(player, true);
        playerData.setBlackCoins(playerData.getBlackCoins()+amount);
    }

    public int getBlackCoinsByPlayer(Player player){
        PlayerData playerData = getPlayer(player, false);
        if(playerData != null){
            return playerData.getBlackCoins();
        }
        return 0;
    }
    
    public int getBlackCoinsByName(String playerName){
        PlayerData playerData = getPlayerByName(playerName);
        if(playerData != null){
            return playerData.getBlackCoins();
        }
        return 0;
    }

    public void updateName(Player player){
        PlayerData playerData = getPlayer(player, false);
        if(playerData != null){
            String newName = player.getName();
            String oldName = playerData.getName();
            if(!newName.equals(oldName)){
                playerNames.remove(oldName);
                playerNames.put(newName,player.getUniqueId());
            }
        }
    }
}
