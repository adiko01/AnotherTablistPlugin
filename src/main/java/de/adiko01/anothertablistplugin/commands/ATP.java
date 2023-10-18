package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ATP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender CommandSender, Command command, String s, String[] args) {

        //Prüfe, ob zu viele oder zu wenige Parameter übergeben wurden
        if (args.length <= 0 || args.length >= 2) {
            CommandSender.sendMessage(ChatColor.RED + "Ussage " + ChatColor.BLUE + "\\atp <about>" + ChatColor.RESET);
            return false;
        }

        if (args[0].equalsIgnoreCase("about")) {
            //Prüfe, ob der Spieler atp.about besitzt
            if (CommandSender instanceof Player) {
                Player p = (Player) CommandSender;
                if (!p.hasPermission("atp.about")) {
                    CommandSender.sendMessage(ChatColor.RED + "This is not allowed! - You need " + ChatColor.BLUE + "atp.about" + ChatColor.RESET);
                    return false;
                }
            }

            CommandSender.sendMessage("AnotherTablistPlugin - Version "
                    + AnotherTablistPlugin.Version + "\n"
                    + "GitHub: " + ChatColor.RED + ChatColor.UNDERLINE + "https://github.com/adiko01/AnotherTablistPlugin" + ChatColor.RESET + "\n"
                    + "Wiki: " + ChatColor.RED + ChatColor.UNDERLINE + "https://github.com/adiko01/AnotherTablistPlugin/wiki" + ChatColor.RESET + "\n"
                    + "Bugtracker: " + ChatColor.RED + ChatColor.UNDERLINE + "https://github.com/adiko01/AnotherTablistPlugin/issues" + ChatColor.RESET + "\n"
                    + "Bukkit: " + ChatColor.RED + ChatColor.UNDERLINE + "https://dev.bukkit.org/projects/anothertablistplugin" + ChatColor.RESET + "\n"
            );
            return false;
        }

        return false;
    }
}
