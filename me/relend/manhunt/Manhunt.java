package me.relend.manhunt;

import me.relend.manhunt.commands.MenuCommand;
import me.relend.manhunt.listeners.ClickListener;
import me.relend.manhunt.listeners.CompassListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class Manhunt extends JavaPlugin {

    static HashMap<Player, String> gameModes = new HashMap<>();

    boolean isPaused = true;
    public static Logger logger = Logger.getLogger("Logger");

    @Override
    public void onEnable() {
        logger.info("[Manhunt] Registering Events...");
        Bukkit.getPluginManager().registerEvents(new CompassListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClickListener(), this);
        logger.info("[Manhunt] Registering Commands...");
        getCommand("manhunt").setExecutor(new MenuCommand());
        logger.info("[Manhunt] Success! Plugin has started.");
    }

    public static void addRunner(Player player) {
        if(gameModes.containsKey(player)) {
            return;
        } else {
            gameModes.put(player, "Runner");
        }
    }

    public static void addHunter(Player player) {
        if(gameModes.containsKey(player)) {
            return;
        } else {
            gameModes.put(player, "Hunter");
        }
    }

    public static boolean isRunner(Player player) {
        if(gameModes.containsKey(player)) {
            if(gameModes.containsValue("Runner")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isHunter(Player player) {
        if(gameModes.containsKey(player)) {
            if(gameModes.containsValue("Hunter")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void removePlayer(Player player) {
        gameModes.remove(player);
    }
}
