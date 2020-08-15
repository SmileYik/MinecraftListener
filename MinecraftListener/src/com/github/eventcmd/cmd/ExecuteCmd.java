package com.github.eventcmd.cmd;

import com.github.eventcmd.EventCommand;
import org.bukkit.entity.Player;


public class ExecuteCmd {
  private static EventCommand plugin;
  
  public static void inti(EventCommand plugin) {
    ExecuteCmd.plugin = plugin;
  }
  
  public static void execute(String cmd, Player p) {
    plugin.getServer().dispatchCommand(
        plugin.getServer().getConsoleSender(), cmd.replace("%p", p.getName()));
  }
}
