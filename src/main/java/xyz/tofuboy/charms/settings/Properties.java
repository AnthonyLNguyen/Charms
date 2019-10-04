package xyz.tofuboy.charms.settings;

import xyz.tofuboy.charms.CharmsPlugin;

public class Properties extends DataFile {

    public Properties(String fileName, CharmsPlugin plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }
    public void loadProperties() {
    }

}
