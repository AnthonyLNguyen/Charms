package xyz.tofuboy.charms;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.tofuboy.charms.charms.CharmManager;
import xyz.tofuboy.charms.command.CommandHandler;
import xyz.tofuboy.charms.settings.CharmProperties;
import xyz.tofuboy.charms.settings.Properties;
import xyz.tofuboy.charms.settings.Messages;

public class Charms extends JavaPlugin {
    private Properties properties;
    private Messages messages;
    private CharmProperties charmProperties;
    private CharmManager charmManager;

    public Charms(){
    }

    @Override
    public void onEnable() {
        initConfigs();
        this.charmManager = new CharmManager();
        registerCommands();
        getLogger().info("onEnable is called!");
    }
    @Override
    public void onDisable() {
        initConfigs();
        charmManager = null;
        getLogger().info("onDisable is called!");
    }

    public void reloadConfig(){
        nullConfigs();
        initConfigs();
    }

    public void initConfigs(){
        this.properties = new Properties("config.yml", this);
        this.messages = new Messages("messages.yml", this);
    }

    public void nullConfigs(){
        this.properties = null;
        this.messages = null;
    }

    private void registerCommands () {
        this.getCommand("charms").setExecutor(new CommandHandler(this));
        this.getCommand("charms").setTabCompleter(new CommandHandler(this));
    }

    public Properties getProperties() {
        return properties;
    }

    public Messages getMessages() {
        return messages;
    }

    public CharmManager getCharmManager() {
        return charmManager;
    }

    public CharmProperties getCharmProperties() {
        return charmProperties;
    }
}
