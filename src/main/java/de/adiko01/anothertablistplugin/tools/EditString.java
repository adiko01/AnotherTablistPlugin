package de.adiko01.anothertablistplugin.tools;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import de.adiko01.anothertablistplugin.tools.TimeTool;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EditString {

    public static String withoutParam(String str) {
        return str
                .replace("{players}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{time}", TimeTool.getTime())
                .replace("{time2}", TimeTool.getTime2())
                .replace("{date}", TimeTool.getDate())
                .replace("{weekday}", TimeTool.getWeekDay())
                .replace("{month}", TimeTool.getMonth()
        );
    }
}
