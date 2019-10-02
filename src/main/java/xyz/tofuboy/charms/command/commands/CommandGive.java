package xyz.tofuboy.charms.command.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.command.ICommand;
import xyz.tofuboy.charms.settings.CharmProperties;

import java.util.ArrayList;
import java.util.List;

public final class CommandGive implements ICommand {

    private final int minArgs = 3;
    private final int maxArgs = 4;
    private final String label = "give";
    private final String perm = "charms.give";
    private final String desc = "Gives player a charm";
    private final String use = "/charms give <player> <charm> <amount>";
    private final boolean isConsole = true;
    private final boolean isPlayer = true;

    public String getDescription() {
        return desc;
    }

    public List<String> tabComplete(Charms plugin, CommandSender sender, String[] args) {
        List<String> list = new ArrayList<>();
        int argsLength = args.length;
        if (argsLength == 0){
            return null;
        } else if ( argsLength == 1 ) {
            list.add("Player");
        } else if( argsLength == 2 ) {
            list.add("charm");
        } else if ( argsLength == 3 ) {
            list.add("count");
        }

        return list;
    }

    public String getUsage() {
        return use;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public void perform(Charms plugin, CommandSender sender, String[] args) {
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(plugin.getMessages().ERROR + args[1] + " is not online or does not exist.");
        } else {
            CharmProperties charmProperties = plugin.getCharmManager().getCharmProperties(args[2]);
            if (charmProperties == null){
                sender.sendMessage(plugin.getMessages().ERROR + args[2] + " is not a valid charm.");
            } else {
                int amount;
                try {
                    amount = Integer.valueOf(args[3]);
                    if (amount <= 0) {
                        sender.sendMessage(plugin.getMessages().ERROR + "amount must not be negative.");
                        return;
                    }
                } catch (NumberFormatException e){
                    sender.sendMessage(plugin.getMessages().ERROR + args[3] + " is not a number.");
                    return;
                }
                ItemStack item = charmProperties.getItemStack(args[2].toUpperCase());
                item.setAmount(amount);
                player.getInventory().addItem(item);
                sender.sendMessage(plugin.getMessages().GIVE + args[2] + " given to " + player.getName());
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public String getPermission() {
        return perm;
    }

    public int getMaxArgs() {
        return maxArgs;
    }

    public boolean isBoth() {
        return isConsole && isPlayer;
    }

    public boolean isConsole() {
        return isConsole;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
