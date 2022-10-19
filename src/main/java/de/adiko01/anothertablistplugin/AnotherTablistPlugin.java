package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.events.PlayerLeaveEvent;
import de.adiko01.anothertablistplugin.events.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class AnotherTablistPlugin extends JavaPlugin {


    public static AnotherTablistPlugin instance;
    public String HEADER;
    public String FOOTER;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        createCustomConfig();
        saveDefaultConfig();
        HEADER = AnotherTablistPlugin.instance.getConfig().getString("header");
        FOOTER = AnotherTablistPlugin.instance.getConfig().getString("footer");
        if (HEADER.contains("{time}") || (HEADER.contains("{seconds}") || (HEADER.contains("{date}")) || (HEADER.contains("{weekday}")) || (HEADER.contains("{month}")) ||FOOTER.contains("{time}") || (FOOTER.contains("{seconds}") || (FOOTER.contains("{date}")) || (FOOTER.contains("{weekday}")) || (FOOTER.contains("{month}"))))) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
                run();
            }, 0, 20);
        }
        getLogger().info("AnotherTablistPlugin is enabeld.");
    }
    private void run() {
        Tablist.SetTablist();
    }

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnotherTablistPlugin is disabeld.");
    }
}
