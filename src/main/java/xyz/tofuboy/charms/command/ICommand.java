package xyz.tofuboy.charms.command;

import org.bukkit.command.CommandSender;
import xyz.tofuboy.charms.Charms;

import java.util.List;

public interface ICommand {
    String getDescription();

    List<String> tabComplete(Charms charms, CommandSender sender, String[] args);

    String getUsage();

    int getMinArgs();

    void perform(Charms charms, CommandSender sender, String[] args);

    String getLabel();

    String getPermission();

    int getMaxArgs();

    boolean isBoth();

    boolean isConsole();

    boolean isPlayer();
}
