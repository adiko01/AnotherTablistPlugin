package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class ATP_CONSOLE implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender CommandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        boolean showHELP = false;

        if (args.length != 1) {
            //Prüfe, ob zu viele oder zu wenige Parameter übergeben wurden
            showHELP = true;
        } else {
            //Anzahl Argumente stimmt
            //Prüfe, ob Sender die Konsole ist
            if (!(CommandSender instanceof ConsoleCommandSender || CommandSender instanceof RemoteConsoleCommandSender)) {
                getLogger().warning(CommandSender.getName() + " wants to use a console only comand.");
                CommandSender.sendMessage(ChatColor.RED + "Der Befehl kann nur in der Konsole verwendet werden!" + ChatColor.RESET);
                return false;
            }

            if (args[0].equalsIgnoreCase("config-cleanup")) {
                //TODO MAKE This Happen
            } else if (args[0].equalsIgnoreCase("help")) {
                showHELP = true;
            }
        }

        if (showHELP) {
            CommandSender.sendMessage(
                    ChatColor.YELLOW +"--------- Help: /atp-console ----------------------------" + "\n"
                            + ChatColor.GOLD + "Description:" + ChatColor.RESET + " Below is a list of all " + ChatColor.GOLD + "/atp-console " + ChatColor.RESET + " commands:" + "\n"
                            //TODO Entferne nach dem implementieren das Durchgestrichene
                            + ChatColor.STRIKETHROUGH + ChatColor.GOLD + "/atp-console config-cleanup :" + ChatColor.RESET +  ChatColor.STRIKETHROUGH + " Generates a new configuration file with the current installations, deleting all comments. Please make a backup before." + "\n"
                            + ChatColor.GOLD + "/atp-console help :" + ChatColor.RESET + " Displays this page." + "\n"
            );
            return false;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender CommandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) CommandSender;

        //Liste aller für den Spieler erlaubten Befehle
        ArrayList<String> Erlaubt = new ArrayList<>();

        Erlaubt.add("config-cleanup");
        Erlaubt.add("help");

        //Liste, welche zurückgegeben werden soll
        ArrayList<String> Ret = new ArrayList<>();
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
