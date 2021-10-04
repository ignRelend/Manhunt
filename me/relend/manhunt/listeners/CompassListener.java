package me.relend.manhunt.listeners;

import me.relend.manhunt.Manhunt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class CompassListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            if(player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS) && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(colorize("&7&lTracker"))) {
                if(Manhunt.isHunter(player)) {
                    if(getNearestRunner(player) == null) {
                        player.sendMessage(colorize("&cNo player in your world to target!"));
                    } else {
                        player.sendMessage(colorize("&aNow tracking " + getNearestRunner(player).getName() + "&a!"));
                        player.setCompassTarget(getNearestRunner(player).getLocation());
                    }
                }
            }
        }
    }

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public Entity getNearestRunner(Player player) {
        boolean found = false;
        for (int i = 0; i < 200; i++) {
            List<Entity> entities = player.getNearbyEntities(i,64,i);
            for (Entity e : entities) {
                if (e.getType().equals(EntityType.PLAYER)) {
                    Player entity = (Player) e;
                    if(Manhunt.isRunner(entity)) {
                        found = true;
                        return e;
                    }
                }
            }
            if (found) break;
        }
        return null;
    }

}
