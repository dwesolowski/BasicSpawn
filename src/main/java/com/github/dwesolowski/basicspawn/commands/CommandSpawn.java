package com.github.dwesolowski.basicspawn.commands;

import com.github.dwesolowski.basicspawn.BasicSpawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class CommandSpawn implements TabExecutor {
    private final BasicSpawn parent;

    public CommandSpawn(BasicSpawn parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length == 0) {
            if (this.parent.getConfig().get("serverSpawn") == null) {
                p.sendMessage(ChatColor.RED + "The server spawn has not been set!");
                return true;
            }

            Location spawn = (Location) this.parent.getConfig().get("serverSpawn");
            p.teleport(spawn);
            p.sendMessage(ChatColor.GOLD + "Teleported to the server spawn.");
            return true;
        }

        if (args.length == 1 && p.hasPermission("basicspawn.admin")) {
            if (args[0].equalsIgnoreCase("set")) {
                Location playerLoc = p.getLocation();
                this.parent.getConfig().set("serverSpawn", playerLoc);
                this.parent.saveConfig();
                p.sendMessage(ChatColor.GREEN + "The server spawn has been set!");
                return true;

            } else if (args[0].equalsIgnoreCase("clear")) {
                if (this.parent.getConfig().get("serverSpawn") == null) {
                    p.sendMessage(ChatColor.RED + "The server spawn has not been set!");
                    return true;
                }
                this.parent.getConfig().set("serverSpawn", null);
                this.parent.saveConfig();
                p.sendMessage(ChatColor.GOLD + "The server spawn has been cleared!");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> opts = new LinkedList<>();
        if (args.length == 1 && sender.hasPermission("basicspawn.admin")) {
            opts.add("set");
            opts.add("clear");
        }
        return opts;
    }
}


