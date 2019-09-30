package xyz.tofuboy.charms.settings;

/**
 * Created by antho on 9/30/2019.
 */

import xyz.tofuboy.charms.Charms;

import org.bukkit.ChatColor;
import xyz.tofuboy.charms.Charms;

public class Config extends DataFile {

    public String
            test
            ;
    public boolean
            debug
            ;


    public Config(String fileName, Charms plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }
    public void loadProperties() {
        this.debug =  Boolean.parseBoolean(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("debug")));
    }

}

