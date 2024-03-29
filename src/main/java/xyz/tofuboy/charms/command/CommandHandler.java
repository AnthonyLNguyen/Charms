package xyz.tofuboy.charms.command;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.command.commands.CommandGive;
import xyz.tofuboy.charms.settings.Messages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private final CharmsPlugin plugin;
    private final List<ICommand> subCommands;

    public CommandHandler(CharmsPlugin plugin){
        this.plugin = plugin;
        subCommands = new ArrayList<>();
        this.subCommands.add(new CommandGive());
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Iterator iter;
        ICommand subCommand;
        Messages messages = plugin.getMessages();
        if (args.length != 0){
            iter = this.subCommands.iterator();

            while (iter.hasNext()) {
                subCommand = (ICommand)iter.next();
                if (subCommand.getLabel().equalsIgnoreCase(args[0])){
                    if (!subCommand.isBoth()) {
                        if (subCommand.isConsole() && sender instanceof Player) {
                            sender.sendMessage(ChatColor.RED + "Only console can use that command!");
                            return true;
                        }

                        if (subCommand.isPlayer() && sender instanceof ConsoleCommandSender) {
                            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
                            return true;
                        }
                    }
                    if (subCommand.getPermission() != null && !sender.hasPermission(subCommand.getPermission())){
                        sender.sendMessage(messages.NO_PERMISSION);
                        return true;
                    }
                    if (args.length >= subCommand.getMinArgs() && args.length <= subCommand.getMaxArgs()){
                        subCommand.perform(this.plugin, sender, args);
                        return true;
                    }

                    sender.sendMessage(messages.COMMAND_USAGE + subCommand.getUsage());
                    return true;
                }
            }
        }
        iter = this.subCommands.iterator();

        do {
            if (!iter.hasNext()){
                sender.sendMessage(messages.NO_PERMISSION);
                return true;
            }

            subCommand = (ICommand)iter.next();
            if ( sender.hasPermission(subCommand.getPermission())){
                sender.sendMessage(messages.HELP_COMMAND_HEADER);
                Iterator newIter = this.subCommands.iterator();

                do {
                    if (!newIter.hasNext()) {
                        sender.sendMessage(messages.HELP_COMMAND_FOOTER);
                        return true;
                    }

                    ICommand cmd = (ICommand)newIter.next();
                    if (sender.hasPermission(subCommand.getPermission())){
                        sender.sendMessage(messages.HELP_COMMAND_LINE + cmd.getUsage() + " : " + cmd.getDescription());
                    }
                } while (true);
            }
        } while(true);
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 0){

            for (ICommand subCommand : this.subCommands) {
                if (subCommand.getLabel().equalsIgnoreCase(args[0])) {
                    if (subCommand.getPermission() != null && !sender.hasPermission((subCommand.getPermission()))) {
                        return new ArrayList<>();
                    }

                    return subCommand.tabComplete(this.plugin, sender, args);
                }
            }
        }
        List<String> list = new ArrayList<>();
        Iterator newIter = this.subCommands.iterator();

        do {
            if (!newIter.hasNext()){
                return list;
            }

            ICommand subCommand = (ICommand)newIter.next();
            if(subCommand.getPermission() != null && sender.hasPermission(subCommand.getPermission())){
                list.add(subCommand.getLabel());
            }
        } while(true);
    }
}
