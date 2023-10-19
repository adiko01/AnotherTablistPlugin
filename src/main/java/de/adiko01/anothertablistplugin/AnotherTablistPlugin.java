package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.commands.ATP;
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

import static de.adiko01.anothertablistplugin.tools.Wildcard.ContainsTime;
import static de.adiko01.anothertablistplugin.tools.Wildcard.parseNONStaticWildcards;
import static de.adiko01.anothertablistplugin.tools.Wildcard.parseStaticWildcards;

/**Main Class
 * @author adiko01
 * @version 1.1.6
 */
public final class AnotherTablistPlugin extends JavaPlugin {


    public static AnotherTablistPlugin instance;

    /**
     * Text above Tablist
     * @since 1.0.0
     **/
    public String HEADER;

    /**
     * Text behind Tablist
     * @since 1.0.0
     **/
    public String FOOTER;

    /**
     * Die Version
     * @since 1.0.0
     **/
    public static String Version;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        //Config Operationen
        createCustomConfig();
        saveDefaultConfig();

        /*
         * Kompatiblität zu pre 1.1.6 Versionen
         * Erstelle Version: 1 in der Config
         */
        if (!getConfig().contains("Version")) {
            getConfig().set("Version", 1);
        }
        saveConfig();
        loadConf();



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

    /**Lädt die Daten der aus der Config
     * @return true wenn erfolgreich - false wenn nicht
     * @since 1.0.0
     */
    public boolean loadConf () {

        if (getConfig().getInt("Version") > 1) {
            getLogger().warning("Die Konfigurationsdatei wurde fuer eine neurere Version dieses Plugins erstellt.");
            getLogger().warning("The configuration file was created for a newer version of this plugin.");
        }

        HEADER = getConfig().getString("header");
        FOOTER = getConfig().getString("footer");

        HEADER = parseStaticWildcards(HEADER);
        FOOTER = parseStaticWildcards(FOOTER);
        return true;
    }

    private void initCommands() {
        try {
            getCommand("atp").setExecutor(new ATP());
            //getCommand("AnotherTablistPlugin").setExecutor(new About());
        } catch (NullPointerException e) {
            getLogger().warning("ERROR: " + e.getMessage());
        }
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
            getLogger().warning("ERROR: Kann die Konfiguration nicht laden! \n ERROR: " + e.getMessage());
        }
    }

    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&',parseNONStaticWildcards(AnotherTablistPlugin.instance.HEADER)));
            player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', parseNONStaticWildcards(AnotherTablistPlugin.instance.FOOTER)));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnotherTablistPlugin is disabled.");
    }
}
