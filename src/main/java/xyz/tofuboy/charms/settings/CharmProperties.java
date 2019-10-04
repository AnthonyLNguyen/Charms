package xyz.tofuboy.charms.settings;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.charms.ICharm;
import xyz.tofuboy.charms.charms.types.Farming;
import xyz.tofuboy.charms.utils.CustomPlayerHeads;

import java.util.*;

public class CharmProperties extends DataFile {
    CharmsPlugin plugin;

    public HashMap<String,Class<? extends ICharm>> charms = new HashMap<>();
    private HashMap<String,ItemStack> headID = new HashMap<>();
    private HashMap<String,String> description = new HashMap<>();
    private HashMap<String,List<Material>> affectedBlocks = new HashMap<>();
    private HashMap<String,List<EntityType>> affectedEntities = new HashMap<>();

    public CharmProperties(String fileName, CharmsPlugin plugin) {
        super(fileName, plugin);
        this.plugin = plugin;
        this.loadProperties();
    }

    private void loadProperties() {
        String mainPath = "CharmsPlugin.";
        String path;


        charms.put("farming",Farming.class);


        for (String key : charms.keySet()) {
            path = mainPath + key;

            affectedBlocks.put(key, new ArrayList<>());
            affectedEntities.put(key, new ArrayList<>());

            headID.put(key, (this.getConfig().contains(path + ".head")) ? CustomPlayerHeads.getHead(this.getConfig().getString(path + ".head")) : CustomPlayerHeads.getHead("Notch"));
            description.put(key, (this.getConfig().contains(path + ".description")) ? this.getConfig().getString(path + ".description") : "NO_DESCRIPTION");

            if (this.getConfig().contains(path + ".blocks")) {
                for (String mat : this.getConfig().getStringList(path + ".blocks")) {
                    affectedBlocks.get(key).add(Material.getMaterial(mat));
                }
            }

            if (this.getConfig().contains(path + ".mobs")) {
                for (String mob : this.getConfig().getStringList(path + ".mobs")) {
                    affectedEntities.get(key).add(EntityType.valueOf(mob));
                }
            }

        }
    }

    public HashMap<String, Class<? extends ICharm>> getAllCharms() {
        return charms;
    }

    public HashMap<String, ItemStack> getAllHeads() {
        return headID;
    }

    public HashMap<String, String> getAllDescriptions() {
        return description;
    }

    public HashMap<String, List<Material>> getAllBlocks() {
        return affectedBlocks;
    }

    public HashMap<String, List<EntityType>> getAllEntities() {
        return affectedEntities;
    }
}
