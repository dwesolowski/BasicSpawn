package com.github.dwesolowski.basicspawn;

import com.github.dwesolowski.basicspawn.commands.CommandSpawn;
import com.github.dwesolowski.basicspawn.listeners.EventListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicSpawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("spawn").setExecutor(new CommandSpawn(this));
    }
}
