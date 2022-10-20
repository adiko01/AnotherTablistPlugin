package de.adiko01.anothertablistplugin.tools;

import de.adiko01.anothertablistplugin.Vars;

import java.util.Random;

public class RandomTool {
    public static String getRandom () {
        Random ran = new Random();
        int i = ran.nextInt(5);
        return Vars.RANDOM[i];
    }
}