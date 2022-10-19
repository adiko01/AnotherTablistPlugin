package de.adiko01.anothertablistplugin.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }

    public static String getTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }

    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }

    public static String getWeekDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }

    public static String getMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
        Date result = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(result);
    }
}
