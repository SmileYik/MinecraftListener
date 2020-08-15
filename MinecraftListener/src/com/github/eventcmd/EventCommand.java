package com.github.eventcmd;

import com.github.eventcmd.cmd.ExecuteCmd;
import com.github.eventcmd.data.ConfigManager;
import com.github.eventcmd.listener.PlayerUseItemListener;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class EventCommand extends JavaPlugin {
  @Override
  public void onEnable() {
    
    if (!getDataFolder().exists()) {
      getDataFolder().mkdir();
    }
    if (!new File(getDataFolder(), "config.yml").exists()) {
      saveDefaultConfig();
    }
    try {
      reloadConfig();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    new ConfigManager(this);
    ExecuteCmd.inti(this);
    getServer().getPluginManager().registerEvents(new PlayerUseItemListener(), this);
    getLogger().info("Made By: miSkYle");
  }
  
  @Override
  public boolean onCommand(CommandSender sender,  Command command,  String label, String[] args) {
    if (label.equalsIgnoreCase("ecommand")
        && sender.hasPermission("EventCommand.admin")) {
      if (!getDataFolder().exists()) {
        getDataFolder().mkdir();
      }
      if (!new File(getDataFolder(), "config.yml").exists()) {
        saveDefaultConfig();
      }
      try {
        reloadConfig();
      } catch (Exception e) {
        e.printStackTrace();
      }
      new ConfigManager(this);
    }
    return true;
  }
}
