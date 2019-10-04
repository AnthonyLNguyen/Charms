package xyz.tofuboy.charms.settings;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tofuboy.charms.CharmsPlugin;

import java.io.File;
import java.io.IOException;

public abstract class DataFile {
    private File file;
    private FileConfiguration config;

    public DataFile(String fileName, CharmsPlugin charmsPlugin) {
        this.createFile(fileName, charmsPlugin);
        this.loadYaml(fileName, charmsPlugin);
    }

    private void createFile(String fileName, CharmsPlugin charmsPlugin) {
        try {
            if (!charmsPlugin.getDataFolder().exists()) {
                charmsPlugin.getDataFolder().mkdirs();
            }

            this.file = new File(charmsPlugin.getDataFolder(), fileName);
            if (!this.file.exists()) {
                charmsPlugin.getLogger().info(fileName + " not found, creating!");
                charmsPlugin.saveResource(fileName, false);
            } else {
                charmsPlugin.getLogger().info(fileName + " found, loading!");
            }
        } catch (Exception var4) {
            System.out.println(fileName + " failed to load..");
            var4.printStackTrace();
        }

    }

    private void loadYaml(String fileName, CharmsPlugin charmsPlugin) {
        this.config = YamlConfiguration.loadConfiguration(new File(charmsPlugin.getDataFolder(), fileName));
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveFile() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}