package com.github.dwesolowski.basicspawn.listeners;

import com.github.dwesolowski.basicspawn.BasicSpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    private final BasicSpawn parent;

    public EventListener(BasicSpawn parent) {
        this.parent = parent;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (this.parent.getConfig().get("serverSpawn") != null) {
            Player p = e.getPlayer();
            Location spawn = (Location) this.parent.getConfig().get("serverSpawn");
            p.teleport(spawn);
        }
    }
}
