package me.kirkfox.noitemexplode;

import me.kirkfox.noitemexplode.command.NIECommand;
import me.kirkfox.noitemexplode.command.NIETabCompleter;
import me.kirkfox.noitemexplode.listener.EntityDamageByEntityListener;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public final class NoItemExplode extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        PluginCommand cmd = Objects.requireNonNull(getCommand("noitemexplode"));
        cmd.setExecutor(new NIECommand());
        cmd.setTabCompleter(new NIETabCompleter());

        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);

        try {
            RegionStorage.loadWorlds();
            RegionStorage.loadChunks();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        try {
            RegionStorage.saveWorlds();
            RegionStorage.saveChunks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Server getPluginServer() {
        return plugin.getServer();
    }
}
