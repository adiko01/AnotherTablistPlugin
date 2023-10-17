package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.AnotherTablistPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class About implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        commandSender.sendMessage("AnotherTablistPlugin - Version "
                        + AnotherTablistPlugin.Version + "\n"
                        + "GitHub: §4§nhttps://github.com/adiko01/AnotherTablistPlugin§r\n"
                        + "Wiki: §4§nhttps://github.com/adiko01/AnotherTablistPlugin/wiki§r\n"
                        + "Bugtracker: §4§nhttps://github.com/adiko01/AnotherTablistPlugin/issues§r\n"
                        + "Bukkit: §4§nhttps://dev.bukkit.org/projects/anothertablistplugin§r\n"
        );
        return false;
    }
}
