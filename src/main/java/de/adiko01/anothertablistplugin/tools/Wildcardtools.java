package de.adiko01.anothertablistplugin.tools;

import org.bukkit.Bukkit;

public class Wildcardtools {

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

    public static Boolean ContainsTime(String str) {
        if (
                str.contains("{time}") ||
                        str.contains("{TIME}") ||
                        str.contains("{time2}") ||
                        str.contains("{TIME2}") ||
                        str.contains("{date}") ||
                        str.contains("{DATE}") ||
                        str.contains("{weekday}") ||
                        str.contains("{WEEKDAY}") ||
                        str.contains("{month}") ||
                        str.contains("{MONTH}")
        ) {
            return true;

        } else {
            return false;
        }
    }
}
