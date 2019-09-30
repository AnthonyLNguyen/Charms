package xyz.tofuboy.charms.settings;

import org.bukkit.ChatColor;
import xyz.tofuboy.charms.Charms;

public class Properties extends DataFile {

    public Properties(String fileName, Charms plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }
    public void loadProperties() {
    }

}
