package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.commands.About;
import de.adiko01.anothertablistplugin.events.PlayerJoinEvent;
import de.adiko01.anothertablistplugin.events.PlayerLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static de.adiko01.anothertablistplugin.tools.Wildcardtools.ContainsTime;
import static de.adiko01.anothertablistplugin.tools.Wildcardtools.withoutParam;

public final class AnotherTablistPlugin extends JavaPlugin {


    public static AnotherTablistPlugin instance;

    /**
     * Text above Tablist
     **/
    public String HEADER;

    /**
     * Text behind Tablist
     **/
    public String FOOTER;

    /** Die Version **/
    public static String Version;


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
                SetTablist();
            }, 0, 20);
        }
        initCommands();
        initEvents();
        PluginDescriptionFile pdf = this.getDescription();
        Version = pdf.getVersion();
        getLogger().info("AnotherTablistPlugin " + Version + "is enabled.");
    }

    private void initCommands() {
        getCommand("AnotherTablistPlugin").setExecutor(new About());
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
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

    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&',withoutParam(AnotherTablistPlugin.instance.HEADER)));
            player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', withoutParam(AnotherTablistPlugin.instance.FOOTER)));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnotherTablistPlugin is disabled.");
    }
}
