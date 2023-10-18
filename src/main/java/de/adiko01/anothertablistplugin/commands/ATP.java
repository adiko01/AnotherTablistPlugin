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
        boolean showHELP = false;

        if (args.length != 1) {
            //Prüfe, ob zu viele oder zu wenige Parameter übergeben wurden
            showHELP = true;
        } else {
            //Anzahl Argumente stimmt
            if (args[0].equalsIgnoreCase("about")) {
                //Prüfe, ob der Spieler atp.about besitzt
                if (CommandSender instanceof Player) {
                    Player p = (Player) CommandSender;
                    if (!p.hasPermission("atp.about")) {
                        getPermError(CommandSender, "atp.about");
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
                    if (!(p.hasPermission("atp.about") || p.hasPermission("atp.bug"))) {
                        getPermError(CommandSender, "atp.about" + ChatColor.RED + " or " + ChatColor.BLUE + "atp.bug");
                        return false;
                    }
                }

                CommandSender.sendMessage("AnotherTablistPlugin - Version "
                        + AnotherTablistPlugin.Version + "\n"
                        + "Bugtracker: " + ChatColor.RED + ChatColor.UNDERLINE + "https://github.com/adiko01/AnotherTablistPlugin/issues" + ChatColor.RESET + "\n"
                );
                return false;
            } else if (args[0].equalsIgnoreCase("help")) {
                showHELP = true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                //Prüfe, ob der Spieler atp.about besitzt
                if (CommandSender instanceof Player) {
                    Player p = (Player) CommandSender;
                    if (!p.hasPermission("atp.reload")) {
                        getPermError(CommandSender, "atp.reload");
                        return false;
                    }
                    AnotherTablistPlugin.instance.reloadConfig();
                    if (AnotherTablistPlugin.instance.loadConf()) {
                        CommandSender.sendMessage(ChatColor.GREEN + "AnotherTablistPlugin has reloaded the Conf!");
                    } else {
                        CommandSender.sendMessage(ChatColor.RED + "AnotherTablistPlugin: Error when trying to reload the configuration!");
                    }
                }
            }
        }

        if (showHELP) {
            //Prüfe, ob der Spieler atp.help besitzt
            if (CommandSender instanceof Player) {
                Player p = (Player) CommandSender;
                if (!p.hasPermission("atp.help")) {
                    getPermError(CommandSender, "atp.help");
                    return false;
                }
            }

            CommandSender.sendMessage(
                    ChatColor.YELLOW +"--------- Help: /atp ----------------------------" + "\n"
                            + ChatColor.GOLD + "Description:" + ChatColor.RESET + " Below is a list of all /atp commands:" + "\n"
                            + ChatColor.GOLD + "/atp about :" + ChatColor.RESET + " Displays information about the plugin." + "\n"
                            + ChatColor.GOLD + "/atp bug :" + ChatColor.RESET + " Displays the link to the BugTracker." + "\n"
                            + ChatColor.GOLD + "/atp help :" + ChatColor.RESET + " Displays this page." + "\n"
                            + ChatColor.GOLD + "/atp reload :" + ChatColor.RESET + " Reloads the plugin." + "\n"
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

        if (p.hasPermission("atp.about")) {
            Erlaubt.add("about");
        }
        if (p.hasPermission("atp.bug") || p.hasPermission("atp.about")) {
            Erlaubt.add("bug");
        }
        if (p.hasPermission("atp.help")) {
            Erlaubt.add("help");
        }
        if (p.hasPermission("atp.reload")) {
            Erlaubt.add("reload");
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

    private void getPermError (CommandSender CommandSender, String permission) {
        CommandSender.sendMessage(ChatColor.RED + "This is not allowed! - You need " + ChatColor.BLUE + permission + ChatColor.RESET);

    }
}
