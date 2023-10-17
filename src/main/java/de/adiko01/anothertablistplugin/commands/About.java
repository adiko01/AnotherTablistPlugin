package de.adiko01.anothertablistplugin.commands;

import de.adiko01.anothertablistplugin.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class About implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        commandSender.sendMessage("AnotherTablistPlugin - Version " + Vars.PuginVer);
        return false;
    }
}
