package xyz.tofuboy.charms.settings;

import org.bukkit.Location;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.charms.CharmType;

/**
 * Created by antho on 10/3/2019.
 */
public class CharmData extends DataFile{
    CharmsPlugin plugin;

    public CharmData(String fileName, CharmsPlugin plugin) {
        super(fileName, plugin);
        this.plugin = plugin;
        this.loadProperties();
    }

    private void loadProperties(){

    }

    public void writeLocation(CharmType charmType, Location location){
        getConfig().set(charmType.toString(), location);
    }
}
