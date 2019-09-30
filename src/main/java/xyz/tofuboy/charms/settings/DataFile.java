package xyz.tofuboy.charms.settings;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.tofuboy.charms.Charms;

import java.io.File;
import java.io.IOException;

public abstract class DataFile {
    private File file;
    private FileConfiguration config;

    public DataFile(String fileName, Charms charms) {
        this.createFile(fileName, charms);
        this.loadYaml(fileName, charms);
    }

    private void createFile(String fileName, Charms charms) {
        try {
            if (!charms.getDataFolder().exists()) {
                charms.getDataFolder().mkdirs();
            }

            this.file = new File(charms.getDataFolder(), fileName);
            if (!this.file.exists()) {
                charms.getLogger().info(fileName + " not found, creating!");
                charms.saveResource(fileName, false);
            } else {
                charms.getLogger().info(fileName + " found, loading!");
            }
        } catch (Exception var4) {
            System.out.println(fileName + " failed to load..");
            var4.printStackTrace();
        }

    }

    private void loadYaml(String fileName, Charms charms) {
        this.config = YamlConfiguration.loadConfiguration(new File(charms.getDataFolder(), fileName));
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