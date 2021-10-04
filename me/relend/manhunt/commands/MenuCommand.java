package me.relend.manhunt.commands;

import me.relend.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                openGUI(player);
            } else {
                if(args[0].equalsIgnoreCase("addrunner")) {
                    if(args[1] != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(Bukkit.getOnlinePlayers().contains(target)) {
                            if(!Manhunt.isHunter(target) && !Manhunt.isRunner(target)) {
                                Manhunt.addRunner(target);
                                player.sendMessage(colorize("&aSuccessfully set " + target.getName() + " &ato a runner!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f ,1f);
                            } else {
                                player.sendMessage(colorize("&cThis player is unable to be added to the runners list!"));
                            }
                        } else {
                            player.sendMessage(colorize("&cThis player is not online!"));
                        }
                    } else {
                        player.sendMessage(colorize("&c/manhunt | /manhunt addrunner <player> | /manhunt addhunter <player>"));
                        player.sendMessage(colorize("&c/manhunt removerunner <player> | /manhunt removehunter <player>"));
                    }
                } else if(args[0].equalsIgnoreCase("addhunter")) {
                    if(args[1] != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(Bukkit.getOnlinePlayers().contains(target)) {
                            if(!Manhunt.isRunner(target) && !Manhunt.isRunner(target)) {
                                Manhunt.addHunter(target);
                                player.sendMessage(colorize("&aSuccessfully set " + target.getName() + " &ato a hunter!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f ,1f);

                                ItemStack compass = new ItemStack(Material.COMPASS);
                                ItemMeta compassMeta = compass.getItemMeta();
                                compassMeta.setDisplayName(colorize("&7&lTracker"));
                                compass.setItemMeta(compassMeta);

                                player.getInventory().addItem(compass);

                            } else {
                                player.sendMessage(colorize("&cThis player is unable to be added to the hunters list!"));
                            }
                        } else {
                            player.sendMessage(colorize("&cThis player is not online!"));
                        }
                    } else {
                        player.sendMessage(colorize("&c/manhunt | /manhunt addrunner <player> | /manhunt addhunter <player>"));
                        player.sendMessage(colorize("&c/manhunt removerunner <player> | /manhunt removehunter <player>"));
                    }
                } else if(args[0].equalsIgnoreCase("removerunner")) {
                    if (args[1] != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (Bukkit.getOnlinePlayers().contains(target)) {
                            if(Manhunt.isRunner(target)) {
                                Manhunt.removePlayer(target);
                                player.sendMessage(colorize("&aSuccessfully removed " + target.getName() + " &afrom a runner!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f ,1f);
                            } else {
                                player.sendMessage(colorize("&cThis player is unable to be removed from the runners list!"));
                            }
                        } else {
                            player.sendMessage(colorize("&cThis player is not online!"));
                        }
                    } else {
                        player.sendMessage(colorize("&c/manhunt | /manhunt addrunner <player> | /manhunt addhunter <player>"));
                        player.sendMessage(colorize("&c/manhunt removerunner <player> | /manhunt removehunter <player>"));
                    }
                } else if(args[0].equalsIgnoreCase("removehunter")) {
                    if (args[1] != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (Bukkit.getOnlinePlayers().contains(target)) {
                            if (Manhunt.isHunter(target)) {
                                Manhunt.removePlayer(target);
                                player.sendMessage(colorize("&aSuccessfully removed " + target.getName() + " &afrom a hunter!"));
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                                player.getInventory().remove(Material.COMPASS);
                            } else {
                                player.sendMessage(colorize("&cThis player is unable to be removed from the hunter list!"));
                            }
                        } else {
                            player.sendMessage(colorize("&cThis player is not online!"));
                        }
                    } else {
                        player.sendMessage(colorize("&c/manhunt | /manhunt addrunner <player> | /manhunt addhunter <player>"));
                        player.sendMessage(colorize("&c/manhunt removerunner <player> | /manhunt removehunter <player>"));
                    }
                } else {
                    player.sendMessage(colorize("&c/manhunt | /manhunt addrunner <player> | /manhunt addhunter <player>"));
                    player.sendMessage(colorize("&c/manhunt removerunner <player> | /manhunt removehunter <player>"));
                }

            }
        } else {
            System.out.println("This command can only be used by players!");
        }
        return false;
    }

    public static void openGUI(Player player) {

        List<String> infoLore = new ArrayList<>();
        infoLore.add(colorize("&7Made by &6Relend&7."));
        infoLore.add("");
        infoLore.add(colorize("&7This plugin is based off"));
        infoLore.add(colorize("&7Dream's manhunt series where"));
        infoLore.add(colorize("&7he tries to beat the game while"));
        infoLore.add(colorize("&7his friends try to kill him."));


        Inventory menu = Bukkit.createInventory(null, 27, "Manhunt Menu");

        ItemStack hunters = new ItemStack(Material.BOW);
        ItemMeta huntersMeta = hunters.getItemMeta();
        huntersMeta.setDisplayName(colorize("&e&lHunters"));
        hunters.setItemMeta(huntersMeta);

        ItemStack runners = new ItemStack(Material.ENDER_EYE);
        ItemMeta runnersMeta = runners.getItemMeta();
        runnersMeta.setDisplayName(colorize("&e&lRunners"));
        runners.setItemMeta(runnersMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(colorize("&c&lClose"));
        close.setItemMeta(closeMeta);

        ItemStack info = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName(colorize("&6&lPlugin Information"));
        infoMeta.setLore(infoLore);
        info.setItemMeta(infoMeta);

        menu.setItem(11, hunters);
        menu.setItem(15, runners);
        menu.setItem(22, close);
        menu.setItem(26, info);

        player.openInventory(menu);
    }

    public void openHuntersGUI(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "Hunters List");

        player.openInventory(menu);
    }

    public void openRunnersGUI(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "Runners List");

        player.openInventory(menu);
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
