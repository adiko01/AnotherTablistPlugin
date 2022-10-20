package de.adiko01.anothertablistplugin.tools;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import de.adiko01.anothertablistplugin.Tablist;
import de.adiko01.anothertablistplugin.Vars;
import org.bukkit.Bukkit;

import java.util.Random;

import static de.adiko01.anothertablistplugin.Vars.setRefTicks;

public class RandomTool {
    // Counter for the Random strings
    public static int iForRandom;
    // The Random Strings
    public static final String[] RANDOM = new String[100];
    /**
     * Time ti live for generated Numbers
     */
    public static int RandScheduer;

    /**
     * Wich String is choosen
     */
    private static int MagicNumber;



    public static void setRandomType(String type) {
        if (type.equals("s")) {
            setRefTicks(20);
            RandScheduer = 20;
        } else if (type.equals("m")) {
            setRefTicks(1200);
            RandScheduer = 1200;
        } else if (type.equals("h")) {
            setRefTicks(72000);
            RandScheduer = 72000;
        } else if (type.equals("d")) {
            setRefTicks(1728000);
            RandScheduer = 1728000;
        } else {
            //Minecraft Day 20 Minuten bzw. 24000 Ticks
            setRefTicks(24000);
            RandScheduer = 24000;
        }
    }
    public static String getRandom () {
        return RANDOM[MagicNumber];
    }
    public static void setRandom() {
        Random ran = new Random();
        MagicNumber = ran.nextInt(iForRandom);
    }
}