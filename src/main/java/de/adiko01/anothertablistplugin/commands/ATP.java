package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ATP implements CommandExecutor , TabCompleter {
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
        } else if (args[0].equalsIgnoreCase("bug")) {
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
                    + "Bugtracker: " + ChatColor.RED + ChatColor.UNDERLINE + "https://github.com/adiko01/AnotherTablistPlugin/issues" + ChatColor.RESET + "\n"
            );
            return false;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender CommandSender, Command command, String s, String[] args) {
        Player p = (Player) CommandSender;

        //Liste aller für den Spieler erlaubten Befehle
        ArrayList<String> Erlaubt = new ArrayList<String>();

        if (p.hasPermission("atp.about") || p.hasPermission("atp.*")) {
            Erlaubt.add("about");
        }

        //Liste, welche zurückgegeben werden soll
        ArrayList<String> Ret = new ArrayList<String>();
        //Aktueller Snippet des Arg
        String CurrentCommand = args[args.length-1];

        for (String MCom : Erlaubt) {
            if (MCom.startsWith(CurrentCommand)) {
                Ret.add(MCom);
            }
        }

        return Ret;
    }
}
