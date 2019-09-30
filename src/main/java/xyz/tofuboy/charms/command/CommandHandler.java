package xyz.tofuboy.charms.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.command.commands.CommandGive;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private final Charms plugin;
    private final List<ICommand> subCommands;

    public CommandHandler(Charms plugin){
        this.plugin = plugin;
        subCommands = new ArrayList<>();
        this.subCommands.add(new CommandGive());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Iterator iter;
        ICommand subCommand;
        if (args.length != 0){
            iter = this.subCommands.iterator();

            while (iter.hasNext()) {
                subCommand = (ICommand)iter.next();
                if (subCommand.getLabel().equalsIgnoreCase(args[0])){
                    if (subCommand.getPermission() != null && !sender.hasPermission(subCommand.getPermission())){
                        sender.sendMessage(plugin.getMessages().NO_PERMISSION);
                        return false;
                    }

                    if (args.length >= subCommand.getMinArgs() && args.length <= subCommand.getMaxArgs()){
                        subCommand.perform(this.plugin, sender, args);
                        return true;
                    }

                    sender.sendMessage(plugin.getMessages().COMMAND_USAGE + subCommand.getUsage());
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
