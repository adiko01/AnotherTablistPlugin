package de.adiko01.anothertablistplugin.events;

import de.adiko01.anothertablistplugin.Tablist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLeaveEvent implements Listener {
    @EventHandler
    public void onPlayerLeave(org.bukkit.event.player.PlayerQuitEvent event) {
        Tablist.SetTablist();
    }
}
