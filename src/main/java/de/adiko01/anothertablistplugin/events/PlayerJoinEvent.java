package de.adiko01.anothertablistplugin.events;

import de.adiko01.anothertablistplugin.Tablist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Tablist.SetTablist();
    }
}
