package com.github.eventcmd.listener;

import com.github.eventcmd.cmd.ExecuteCmd;
import com.github.eventcmd.data.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class PlayerUseItemListener implements Listener {
  /**
   * 处理PlayerInteractEvent.
   * @param event 玩家交互事件
   */
  @SuppressWarnings("deprecation")
  @EventHandler
  public void onPlayerUseItem(final PlayerInteractEvent event) {
    if (event.isCancelled()) {
      return;
    }
    if (event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if (event.hasItem()) {
        if (ConfigManager.getPlayerUseItemCmd().containsKey(event.getItem().getType())) {
          ConfigManager.getPlayerUseItemCmd().get(event.getItem().getType()).forEach(entry -> {
            if (Math.random() * 100 < entry.getLeft()) {
              ExecuteCmd.execute(entry.getRight(), event.getPlayer());
            }
          });
        }
      }
    }
  }
  
  /**
   * 处理PlayerInteractEvent.
   * @param event 玩家交互事件
   */
  @EventHandler
  public void onPlayerUseItem(final PlayerItemConsumeEvent event) {
    if (event.isCancelled()) {
      return;
    }
    if (event.getItem() != null) {
      if (ConfigManager.getPlayerUseItemCmd2().containsKey(event.getItem().getType())) {
        ConfigManager.getPlayerUseItemCmd2().get(event.getItem().getType()).forEach(entry -> {
          if (Math.random() * 100 < entry.getLeft()) {
            ExecuteCmd.execute(entry.getRight(), event.getPlayer());
          }
        });
      }
    }
  }
  
  /**
   * 撸羊毛.
   * @param event 事件
   */
  @EventHandler
  public void onShear(final PlayerShearEntityEvent event) {
    if (event.isCancelled()) {
      return;
    }
    if (event.getEntity().getType() == EntityType.SHEEP) {
      ConfigManager.getShearSheep().forEach(entry -> {
        if (Math.random() * 100 < entry.getLeft()) {
          ExecuteCmd.execute(entry.getRight(), event.getPlayer());
        }
      });
    }
  }
  
  /**
   * 挤牛奶事件.
   * @param event 事件
   */
  @EventHandler
  public void onGetMilk(final PlayerInteractAtEntityEvent event) {
    if (event.isCancelled()) {
      return;
    }
    ItemStack item = getItemInHand(event.getPlayer(), event.getHand());
    if (item == null || item.getType() != Material.BUCKET 
        || event.getRightClicked().getType() != EntityType.COW) {
      return;
    }
    ConfigManager.getMilkCow().forEach(entry -> {
      if (Math.random() * 100 < entry.getLeft()) {
        ExecuteCmd.execute(entry.getRight(), event.getPlayer());
      }
    });
  }
  
  private ItemStack getItemInHand(Player p, EquipmentSlot hand) {
    if (hand == EquipmentSlot.HAND) {
      return p.getInventory().getItemInMainHand();
    } else {
      return p.getInventory().getItemInOffHand();
    }
  }
  
}
