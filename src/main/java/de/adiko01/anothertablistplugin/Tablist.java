package de.adiko01.anothertablistplugin;

import de.adiko01.anothertablistplugin.tools.TimeTool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tablist {
    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', de.adiko01.anothertablistplugin.tools.EditString.withoutParam(AnotherTablistPlugin.instance.HEADER)));
            player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', de.adiko01.anothertablistplugin.tools.EditString.withoutParam(AnotherTablistPlugin.instance.FOOTER)));
        }
    }
}
