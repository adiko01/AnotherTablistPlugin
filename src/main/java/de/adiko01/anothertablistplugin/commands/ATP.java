package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ATP implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender CommandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
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
    public List<String> onTabComplete(@NotNull CommandSender CommandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        //Liste aller Argumente des Commmand
        String[][] Commands = {
                //command snippet , permission
                {"about" , "atp.about"},
                {"help" , "atp.help"},
                {"bug" , "atp.about"},
                {"bug" , "atp.bug"},
                {"reload" , "atp.reload"}
        };

        //Liste, welche zurückgegeben werden soll
        ArrayList<String> Ret = new ArrayList<>();
        //Aktueller Snippet des Arg
        String CurrentCommand = args[args.length-1];

        Player p;
        if (CommandSender instanceof Player) {
            p = (Player) CommandSender;
        } else {
            p = null;
        }

        for (String MCom[] : Commands) {
            if (MCom[0].startsWith(CurrentCommand)) {
                if ((p != null) && !p.hasPermission(MCom[1])) {
                    continue;
                }
                Ret.add(MCom[0]);
            }
        }

        return Ret;
    }

    private void getPermError (CommandSender CommandSender, String permission) {
        CommandSender.sendMessage(ChatColor.RED + "This is not allowed! - You need " + ChatColor.BLUE + permission + ChatColor.RESET);

    }
}
