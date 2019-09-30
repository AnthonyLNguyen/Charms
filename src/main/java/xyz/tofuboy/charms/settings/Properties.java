package xyz.tofuboy.charms.settings;

import org.bukkit.ChatColor;
import xyz.tofuboy.charms.Charms;

public class Properties extends DataFile {

    public Properties(String fileName, Charms charms) {
        super(fileName, charms);
        this.loadProperties();
    }
    public void loadProperties() {
    }

}
