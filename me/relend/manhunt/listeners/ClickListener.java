package me.relend.manhunt.listeners;

import me.relend.manhunt.Manhunt;
import me.relend.manhunt.commands.MenuCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getView().getTitle().equals("Manhunt Menu")) {
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&6&lPlugin Information"))) {
                event.setCancelled(true);
                player.sendMessage(colorize("&7You can check out the developer's &cYou&fTube &7channel here!"));
                player.sendMessage(colorize("&6https://www.youtube.com/channel/UCRmP56xeXj_dl5q8xW2LfVQ"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                player.closeInventory();
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&c&lClose"))) {
                event.setCancelled(true);
                player.closeInventory();
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&e&lRunners"))) {
                event.setCancelled(true);
                player.closeInventory();
                openRunnersGUI(player);
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&e&lHunters"))) {
                event.setCancelled(true);
                player.closeInventory();
                openHuntersGUI(player);
            }
        } else if(event.getView().getTitle().equals("Hunters List")) {
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&f&lBack"))) {
                event.setCancelled(true);
                player.closeInventory();
                MenuCommand.openGUI(player);
            } else if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                String playerName = event.getCurrentItem().getItemMeta().getLocalizedName();
                Player target = Bukkit.getPlayer(playerName);

                Manhunt.removePlayer(target);
                player.sendMessage(colorize("&aRemoved " + target.getName() + " &afrom the hunters list!"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                player.closeInventory();
                openHuntersGUI(player);
            }


        } else if(event.getView().getTitle().equals("Runners List")) {
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals(colorize("&f&lBack"))) {
                event.setCancelled(true);
                player.closeInventory();
                MenuCommand.openGUI(player);
            } else if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                String playerName = event.getCurrentItem().getItemMeta().getLocalizedName();
                Player target = Bukkit.getPlayer(playerName);

                Manhunt.removePlayer(target);
                player.sendMessage(colorize("&aRemoved " + target.getName() + " &afrom the runners list!"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                player.closeInventory();
                openRunnersGUI(player);
            }
        }
    }
    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void openHuntersGUI(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "Hunters List");

        for(Player all : Bukkit.getOnlinePlayers()) {
            if(Manhunt.isHunter(all)) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta headMeta = (SkullMeta) head.getItemMeta();
                headMeta.setOwningPlayer(all);
                headMeta.setDisplayName(colorize("&e&l" + all.getName()));
                headMeta.setLocalizedName(all.getName());
                head.setItemMeta(headMeta);

                menu.addItem(head);
            }
        }

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(colorize("&f&lBack"));
        back.setItemMeta(backMeta);

        menu.setItem(49, back);
        player.openInventory(menu);
    }

    public void openRunnersGUI(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "Runners List");

        for(Player all : Bukkit.getOnlinePlayers()) {
            if(Manhunt.isRunner(all)) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta headMeta = (SkullMeta) head.getItemMeta();
                headMeta.setOwningPlayer(all);
                headMeta.setDisplayName(colorize("&e&l" + all.getName()));
                headMeta.setLocalizedName(all.getName());
                head.setItemMeta(headMeta);

                menu.addItem(head);
            }
        }



        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(colorize("&f&lBack"));
        back.setItemMeta(backMeta);

        menu.setItem(49, back);
        player.openInventory(menu);
    }



}
