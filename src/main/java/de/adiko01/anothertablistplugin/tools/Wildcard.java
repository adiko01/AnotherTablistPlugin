package de.adiko01.anothertablistplugin.tools;

import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**Klasse zum Laden der Wildcards
 * @version 1.0
 */
public class Wildcard {

    /**
     * Ersetzt die statischen Wildcardsd
     * {MAX-PLAYERS}, {MOTD}
     * @param str Der zu bearbeitende String
     * @return Der ersetzte String
     * @since 1.0
     * @version 1.0
     */
    public static String parseStaticWildcards(String str) {
        return str
                .replace("{max-players}", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("{MAX-PLAYERS}", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("{motd}", String.valueOf(Bukkit.getMotd()))
                .replace("{MOTD}", String.valueOf(Bukkit.getMotd()))
        ;
    }

    /**
     * Ersetzt die NICHT statischen Wildcards
     * {PLAYERS} und alle zeitabhängigen Wildcards
     * @param str Der zu bearbeitende String
     * @return Der ersetzte String
     * @since 1.0
     * @version 1.0
     */
    public static String parseNONStaticWildcards(String str) {
        return str
                .replace("{players}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{PLAYERS}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{time}", formatTime("HH:mm"))
                .replace("{TIME}", formatTime("HH:mm"))
                .replace("{time2}", formatTime("HH:mm:ss"))
                .replace("{TIME2}", formatTime("HH:mm:ss"))
                .replace("{date}", formatTime("dd.MM.yyyy"))
                .replace("{DATE}", formatTime("dd.MM.yyyy"))
                .replace("{weekday}", formatTime("EEE"))
                .replace("{WEEKDAY}", formatTime("EEE"))
                .replace("{month}", formatTime("MMMM"))
                .replace("{Month}", formatTime("MMMM"))
        ;
    }

    /**
     * Prüft, ob ein String einen zeitbasierten Parameter enthält
     * @param str Der zu prüfende String
     * @return TRUE or FALSE
     */
    public static boolean ContainsTime(String str) {
        return str.contains("{time}") ||
                str.contains("{TIME}") ||
                str.contains("{time2}") ||
                str.contains("{TIME2}") ||
                str.contains("{date}") ||
                str.contains("{DATE}") ||
                str.contains("{weekday}") ||
                str.contains("{WEEKDAY}") ||
                str.contains("{month}") ||
                str.contains("{MONTH}");
    }

    /**
     *
     * @param format Das Format für {@link SimpleDateFormat}
     * @return Das gewünschte Datum als String
     * @since 1.0
     * @version 1.0
     */
    private static String formatTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }

}
