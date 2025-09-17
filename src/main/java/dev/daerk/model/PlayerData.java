package dev.daerk.model;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private int BlackCoins;

    public PlayerData(UUID uuid, String name, int BlackCoins) {
        this.uuid = uuid;
        this.name = name;
        this.BlackCoins = BlackCoins;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getBlackCoins() {
        return BlackCoins;
    }

    public void setBlackCoins(int BlackCoins) {
        this.BlackCoins = BlackCoins;
    }
}