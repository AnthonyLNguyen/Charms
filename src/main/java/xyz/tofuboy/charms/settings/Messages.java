package xyz.tofuboy.charms.settings;

import org.bukkit.ChatColor;
import xyz.tofuboy.charms.Charms;

public class Messages extends DataFile {
    public String
            PLUGIN_PREFIX = "",
            ERROR = "",
            GIVE = "",
            NO_PERMISSION = "",
            COMMAND_USAGE = "",
            HELP_COMMAND_HEADER = "",
            HELP_COMMAND_LINE = "",
            HELP_COMMAND_FOOTER = ""

            ;


    public Messages(String fileName, Charms plugin) {
        super(fileName, plugin);
        this.loadProperties(plugin);
    }

    public void loadProperties(Charms plugin) {
        try {
            this.PLUGIN_PREFIX = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("PluginsPrefix"));
            this.ERROR = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Error"));
            this.GIVE = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Give"));
            this.NO_PERMISSION = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("NoPerm"));
            this.COMMAND_USAGE = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CommandUsage"));
            this.HELP_COMMAND_HEADER = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes( '&', "&b=====COMMAND=====");
            this.HELP_COMMAND_LINE = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes( '&', "&b-> ");
            this.HELP_COMMAND_FOOTER = PLUGIN_PREFIX + ChatColor.translateAlternateColorCodes( '&', "&b=================");
        }
        catch (NullPointerException e){
            plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&4Missing information in messages.yml"));
        }
    }
}
