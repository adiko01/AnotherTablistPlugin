package de.adiko01.anothertablistplugin.tools;

import de.adiko01.anothertablistplugin.Vars;

import java.util.Random;

public class RandomTool {
    public static String getRandom () {
        //ToDo Einstellung ermöglichen ob Sekündlich wechsel oder Minütlich wechselnd des Random
        Random ran = new Random();
        int i = ran.nextInt(Vars.iForRandom);
        return Vars.RANDOM[i];
    }
}