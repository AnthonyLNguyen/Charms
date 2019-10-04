package xyz.tofuboy.charms.command;

import org.bukkit.command.CommandSender;
import xyz.tofuboy.charms.CharmsPlugin;

import java.util.List;

public interface ICommand {
    String getDescription();

    List<String> tabComplete(CharmsPlugin charmsPlugin, CommandSender sender, String[] args);

    String getUsage();

    int getMinArgs();

    void perform(CharmsPlugin plugin, CommandSender sender, String[] args);

    String getLabel();

    String getPermission();

    int getMaxArgs();

    boolean isBoth();

    boolean isConsole();

    boolean isPlayer();
}
