package de.adiko01.anothertablistplugin.tools;

import org.bukkit.Bukkit;

public class EditString {

    public static String withoutParam(String str) {
        return str
                .replace("{players}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{PLAYERS}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{max-players}", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("{MAX-PLAYERS}", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("{motd}", String.valueOf(Bukkit.getMotd()))
                .replace("{MOTD}", String.valueOf(Bukkit.getMotd()))
                .replace("{time}", TimeTool.getTime())
                .replace("{TIME}", TimeTool.getTime())
                .replace("{time2}", TimeTool.getTime2())
                .replace("{TIME2}", TimeTool.getTime2())
                .replace("{date}", TimeTool.getDate())
                .replace("{DATE}", TimeTool.getDate())
                .replace("{weekday}", TimeTool.getWeekDay())
                .replace("{WEEKDAY}", TimeTool.getWeekDay())
                .replace("{month}", TimeTool.getMonth())
                .replace("{Month}", TimeTool.getMonth()
                );
    }
}
