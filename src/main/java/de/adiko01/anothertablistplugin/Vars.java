package de.adiko01.anothertablistplugin;

public class Vars {

    /**
     * About the Plugin
     **/

    /**
     * Plugin's version
     **/
    public static String PluginVer;

    /**
     * Text above Tablist
     **/
    public static String HEADER;

    /**
     * Text behind Tablist
     **/
    public static String FOOTER;

    /**
     * Refresh the Tablist in Ticks
     */
    public static int RefTicks = 0;

    /**
     * Getter
     */


    /**
     * Setter
     */

    public static void setRefTicks (int i) {
        if (RefTicks == 0 || RefTicks > i) {
            RefTicks = i;
        } else {
            //Nothing happen here
        }
    }
}
