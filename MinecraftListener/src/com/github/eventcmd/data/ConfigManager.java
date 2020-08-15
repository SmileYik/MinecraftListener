package com.github.eventcmd.data;

import com.github.eventcmd.EventCommand;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Material;

public class ConfigManager {
  private static ConfigManager cm;
  private EventCommand plugin;
  private HashMap<Material, ArrayList<SimpleEntry<Double, String>>> playerUseItemCmd = 
      new HashMap<>();
  private HashMap<Material, ArrayList<SimpleEntry<Double, String>>> playerUseItemCmd2 = 
      new HashMap<>();
  private ArrayList<SimpleEntry<Double, String>> shearSheep = new ArrayList<>();
  private ArrayList<SimpleEntry<Double, String>> milkCow = new ArrayList<>();
  
  /**
   * ³õÊ¼»¯ConfigManager.
   * @param plugin ²å¼þ
   */
  public ConfigManager(EventCommand plugin) {
    this.plugin = plugin;
    cm = this;
    loadPlayerUseItemCmd();
    loadPlayerUseItemCmd2();
    loadShearSheapCmd();
    loadMilkCowCmd();
  }
  
  private void loadMilkCowCmd() {
    plugin.getConfig().getStringList("PlayerInteractAtEntityEvent.COW").forEach(line -> {
      String[] temp = line.split(":");
      shearSheep.add(new SimpleEntry<Double, String>(
          Double.parseDouble(temp[0]), temp[1]));
    });
  }
  
  private void loadShearSheapCmd() {
    plugin.getConfig().getStringList("PlayerShearEntityEvent.SHEEP").forEach(line -> {
      String[] temp = line.split(":");
      shearSheep.add(new SimpleEntry<Double, String>(
          Double.parseDouble(temp[0]), temp[1]));
    });
  }
  
  private void loadPlayerUseItemCmd2() {
    for (Material type : Material.values()) {
      if (plugin.getConfig().contains("PlayerItemConsumeEvent." + type.name())) {
        plugin.getConfig().getStringList("PlayerItemConsumeEvent." + type.name()).forEach(line -> {
          String[] temp = line.split(":");
          if (playerUseItemCmd.containsKey(type)) {
            playerUseItemCmd.get(type).add(new SimpleEntry<Double, String>(
                Double.parseDouble(temp[0]), temp[1]));
          } else {
            ArrayList<SimpleEntry<Double, String>> list = new ArrayList<>();
            list.add(new SimpleEntry<Double, String>(
                Double.parseDouble(temp[0]), temp[1]));
            playerUseItemCmd.put(type, list);
          }
        });
      }
    }
  }
  
  private void loadPlayerUseItemCmd() {
    for (Material type : Material.values()) {
      if (plugin.getConfig().contains("PlayerInteractEvent." + type.name())) {
        plugin.getConfig().getStringList("PlayerInteractEvent." + type.name()).forEach(line -> {
          String[] temp = line.split(":");
          if (playerUseItemCmd.containsKey(type)) {
            playerUseItemCmd.get(type).add(new SimpleEntry<Double, String>(
                Double.parseDouble(temp[0]), temp[1]));
          } else {
            ArrayList<SimpleEntry<Double, String>> list = new ArrayList<>();
            list.add(new SimpleEntry<Double, String>(
                Double.parseDouble(temp[0]), temp[1]));
            playerUseItemCmd.put(type, list);
          }
        });
      }
    }
  }
  
  public static HashMap<Material, ArrayList<SimpleEntry<Double, String>>> getPlayerUseItemCmd() {
    return cm.playerUseItemCmd;
  }
  
  public static HashMap<Material, ArrayList<SimpleEntry<Double, String>>> getPlayerUseItemCmd2() {
    return cm.playerUseItemCmd2;
  }
  
  public static ArrayList<SimpleEntry<Double, String>> getShearSheep() {
    return cm.shearSheep;
  }
  
  public static ArrayList<SimpleEntry<Double, String>> getMilkCow() {
    return cm.milkCow;
  }
}
