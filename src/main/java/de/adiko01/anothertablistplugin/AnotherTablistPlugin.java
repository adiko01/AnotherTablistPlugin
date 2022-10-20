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

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        createCustomConfig();
        saveDefaultConfig();
        initVars();
        if (ContainsTime(Vars.HEADER) || ContainsTime(Vars.FOOTER)) {
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
    private static void initVars() {
        Vars.HEADER = AnotherTablistPlugin.instance.getConfig().getString("header");
        Vars.FOOTER = AnotherTablistPlugin.instance.getConfig().getString("footer");

        //ToDo Move this into a foreach loop
        Vars.RANDOM[0] = AnotherTablistPlugin.instance.getConfig().getString("random0");
        Vars.RANDOM[1] = AnotherTablistPlugin.instance.getConfig().getString("random1");
        Vars.RANDOM[2] = AnotherTablistPlugin.instance.getConfig().getString("random2");
        Vars.RANDOM[3] = AnotherTablistPlugin.instance.getConfig().getString("random3");
        Vars.RANDOM[4] = AnotherTablistPlugin.instance.getConfig().getString("random4");
        Vars.RANDOM[5] = AnotherTablistPlugin.instance.getConfig().getString("random5");
    }
    private void initCommands() {
        getCommand("AnotherTablistPlugin").setExecutor(new AboudCommand());
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnotherTablistPlugin is disabled.");
    }
}
