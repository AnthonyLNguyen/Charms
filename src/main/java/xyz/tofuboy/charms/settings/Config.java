package xyz.tofuboy.charms.settings;

/**
 * Created by antho on 9/30/2019.
 */

import xyz.tofuboy.charms.CharmsPlugin;

import org.bukkit.ChatColor;

public class Config extends DataFile {

    public String
            test
            ;
    public boolean
            debug
            ;


    public Config(String fileName, CharmsPlugin plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }
    public void loadProperties() {
        this.debug =  Boolean.parseBoolean(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("debug")));
    }

}

