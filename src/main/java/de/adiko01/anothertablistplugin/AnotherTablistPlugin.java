package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.commands.AboudCommand;
import de.adiko01.anothertablistplugin.events.PlayerJoinEvent;
import de.adiko01.anothertablistplugin.events.PlayerLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static de.adiko01.anothertablistplugin.tools.Wildcardtools.ContainsTime;

public final class AnotherTablistPlugin extends JavaPlugin {


    public static AnotherTablistPlugin instance;
    public String HEADER;
    /**
     * Text above Tablist
     **/
    public String FOOTER;

    /**
     * Text behind Tablist
     **/


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        createCustomConfig();
        saveDefaultConfig();
        HEADER = AnotherTablistPlugin.instance.getConfig().getString("header");
        FOOTER = AnotherTablistPlugin.instance.getConfig().getString("footer");
        if (ContainsTime(HEADER) || ContainsTime(FOOTER)) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
                run();
            }, 0, 20);
        }
        initCommands();
        initEvents();
        PluginDescriptionFile pdf = this.getDescription();
        Vars.PuginVer = pdf.getVersion();
        getLogger().info("AnotherTablistPlugin " + Vars.PuginVer + "is enabled.");
    }

    private void initCommands() {
        getCommand("AnotherTablistPlugin").setExecutor(new AboudCommand());
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
    }

    private void run() {
        Tablist.SetTablist();
    }

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
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
        getLogger().info("AnotherTablistPlugin is disabled.");
    }
}
