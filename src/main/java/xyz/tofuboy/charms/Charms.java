package xyz.tofuboy.charms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.tofuboy.charms.charms.CharmManager;
import xyz.tofuboy.charms.command.CommandHandler;
import xyz.tofuboy.charms.settings.CharmProperties;
import xyz.tofuboy.charms.settings.Config;
import xyz.tofuboy.charms.settings.Messages;

public class Charms extends JavaPlugin {
    private Config config;
    private Messages messages;
    private CharmProperties charmProperties;
    private CharmManager charmManager;

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

    private void initConfigs(){
        this.config = new Config("config.yml", this);
        this.messages = new Messages("messages.yml", this);
        this.charmProperties = new CharmProperties("charms.yml", this);
    }

    private void nullConfigs(){
        this.config = null;
        this.messages = null;
        this.charmProperties = null;
    }

    private void registerCommands () {
        CommandHandler commandHandler = new CommandHandler(this);
        this.getCommand("charms").setExecutor(commandHandler);
        this.getCommand("charms").setTabCompleter(commandHandler);
    }

    public Config conf() {
        return config;
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

    public enum LogType
    {
        ERROR,
        WARNING,
        INFO,
        DEBUG
    }

    public boolean console (LogType type, String message)
    {
        if (!config.debug && type == LogType.DEBUG){return false;}
        else{
        ChatColor color;
        switch (type)
        {
            case INFO:      color = ChatColor.AQUA;       break;
            case ERROR:     color = ChatColor.RED;        break;
            case WARNING:   color = ChatColor.YELLOW;     break;
            case DEBUG:     color = ChatColor.DARK_GREEN; break;
            default:        color = ChatColor.WHITE;      break;
        }
        Bukkit.getConsoleSender().sendMessage(color + "[Charms] - " + type.name() + " - " + message);
        return true;
        }
    }
}
