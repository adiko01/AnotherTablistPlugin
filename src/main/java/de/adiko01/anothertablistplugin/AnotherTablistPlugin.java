package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.commands.AboudCommand;
import de.adiko01.anothertablistplugin.events.PlayerJoinEvent;
import de.adiko01.anothertablistplugin.events.PlayerLeaveEvent;
import de.adiko01.anothertablistplugin.tools.RandomTool;
import de.adiko01.anothertablistplugin.tools.Wildcardtools;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static de.adiko01.anothertablistplugin.Vars.setRefTicks;
import static de.adiko01.anothertablistplugin.tools.Wildcardtools.ContainsRandom;
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
        initCommands();
        initEvents();
        if (ContainsRandom(Vars.HEADER) || ContainsRandom(Vars.FOOTER)) {
            RandomTool.setRandomType(AnotherTablistPlugin.instance.getConfig().getString("randomtype"));
        }
        if (ContainsTime(Vars.HEADER) || ContainsTime(Vars.FOOTER)) {
            setRefTicks(20);
        }
        setScheduer();
        getLogger().info("AnotherTablistPlugin " + Vars.PluginVer + "is enabled.");
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
    private void initVars() {
        PluginDescriptionFile pdf = this.getDescription();
        Vars.PluginVer = pdf.getVersion();

        Vars.HEADER = AnotherTablistPlugin.instance.getConfig().getString("header");
        Vars.FOOTER = AnotherTablistPlugin.instance.getConfig().getString("footer");

        //ToDo Move this into a foreach loop
        RandomTool.iForRandom = 5;
        RandomTool.RANDOM[0] = AnotherTablistPlugin.instance.getConfig().getString("random0");
        RandomTool.RANDOM[1] = AnotherTablistPlugin.instance.getConfig().getString("random1");
        RandomTool.RANDOM[2] = AnotherTablistPlugin.instance.getConfig().getString("random2");
        RandomTool.RANDOM[3] = AnotherTablistPlugin.instance.getConfig().getString("random3");
        RandomTool.RANDOM[4] = AnotherTablistPlugin.instance.getConfig().getString("random4");
        RandomTool.RANDOM[5] = AnotherTablistPlugin.instance.getConfig().getString("random5");
    }
    private void initCommands() {
        getCommand("AnotherTablistPlugin").setExecutor(new AboudCommand());
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
    }

    private void setScheduer () {
        if (Vars.RefTicks > 0) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::run, 0, Vars.RefTicks);
        } else {
            //Nothing happens here
        }
        if (RandomTool.RandScheduer > 0) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> RandomTool.setRandom(), 0, RandomTool.RandScheduer);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnotherTablistPlugin is disabled.");
    }
}
