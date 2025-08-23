package dev.daerk.tools;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemTools {

    public static ItemStack generateDiamond(int amount){
        ItemStack item = new ItemStack(Material.DIAMOND, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bDiamante"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Diamante de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generateRawGold(int amount){
        ItemStack item = new ItemStack(Material.RAW_GOLD, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bOro crudo"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Oro crudo de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generateRawIron(int amount){
        ItemStack item = new ItemStack(Material.RAW_IRON, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bHierro crudo"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Hierro crudo de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generateCoal(int amount){
        ItemStack item = new ItemStack(Material.COAL, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bCarbon"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Carbon de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generateLapis(int amount){
        ItemStack item = new ItemStack(Material.LAPIS_LAZULI, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bLapis Lazuli"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Lapis Lazuli de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }


    public static ItemStack generateAncienDebris(int amount){
        ItemStack item = new ItemStack(Material.ANCIENT_DEBRIS, amount);
        //ItemMeta meta = item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bEscombro Ancestral"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Escombro Ancestral de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 10%"));
        //meta.setLore(lore);

        //meta.addEnchant(Enchantment.FORTUNE, 1, true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generatePotion(int amount){
        ItemStack item = new ItemStack(Material.SPLASH_POTION, amount);
        //PotionMeta meta = (PotionMeta) item.getItemMeta();
        //meta.setDisplayName(MessageColors.coloredMessage("&bPoción de rescate"));

        //List<String> lore = new ArrayList<>();
        //lore.add(MessageColors.coloredMessage("&7Poción de rescate de &a&lKiwi&f&lblack&7."));
        //lore.add(MessageColors.coloredMessage("&7por sistema de suerte."));
        //lore.add(MessageColors.coloredMessage(""));
        //lore.add(MessageColors.coloredMessage("&7Probabilidad: 50%"));
        //meta.setLore(lore);

        //meta.setColor(Color.fromRGB(0, 0, 0));
        //meta.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 1, false, false),true);
        //meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false),true);
        //meta.addCustomEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 4, false, false),true);

        //meta.addItemFlags(ItemFlag.HIDE_POTION_CONTENTS);

        //item.setItemMeta(meta);

        return item;
    }
}
