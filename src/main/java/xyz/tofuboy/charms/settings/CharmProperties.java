package xyz.tofuboy.charms.settings;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.charms.ICharm;
import xyz.tofuboy.charms.charms.types.Farming;

import java.util.*;

public class CharmProperties extends DataFile {
    public HashMap<String,Class<? extends ICharm>> charms = new HashMap<>();
    private HashMap<Class<? extends ICharm>,String> name = new HashMap<>();
    private HashMap<Class<? extends ICharm>,String> description = new HashMap<>();
    private HashMap<Class<? extends ICharm>,Material> item = new HashMap<>();
    private HashMap<Class<? extends ICharm>,List<Material>> affectedBlocks = new HashMap<>();
    private HashMap<Class<? extends ICharm>,List<EntityType>> affectedEntities = new HashMap<>();

    public CharmProperties(String fileName, Charms plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }

    public void loadProperties() {

        charms.put("FARMER",Farming.class);


        for (Map.Entry aClass : charms.entrySet()) {
            try {
                item.put((Class<? extends ICharm>)aClass.getValue(), Material.getMaterial(this.getConfig().getString(charms.get(aClass) + ".item")));
            } catch (NullPointerException e){
                item.put((Class<? extends ICharm>)aClass.getValue(), Material.CREEPER_HEAD);
            }

            affectedBlocks.put((Class<? extends ICharm>)aClass.getValue(),new ArrayList<Material>());
            affectedEntities.put((Class<? extends ICharm>)aClass.getValue(),new ArrayList<EntityType>());

            if (this.getConfig().contains(charms.get((Class<? extends ICharm>)aClass.getValue()) + ".name")) {
                name.put((Class<? extends ICharm>)aClass.getValue(), this.getConfig().getString(charms.get(aClass.getKey()) + ".name"));
            }

            if (this.getConfig().contains(charms.get((Class<? extends ICharm>)aClass.getValue()) + ".description")) {
                description.put((Class<? extends ICharm>)aClass.getValue(), this.getConfig().getString(charms.get(aClass.getKey()) + ".description"));
            }

            if (this.getConfig().contains(charms.get((Class<? extends ICharm>)aClass.getValue()) + ".blocks")) {
                for (String mat : this.getConfig().getStringList(charms.get(aClass.getKey()) + ".blocks")) {
                    affectedBlocks.get((Class<? extends ICharm>)aClass.getValue()).add(Material.getMaterial(mat));
                }
            }

            if (this.getConfig().contains(charms.get((Class<? extends ICharm>)aClass.getValue()) + ".mobs")) {
                for (String mob : this.getConfig().getStringList(charms.get(aClass.getKey()) + ".mobs")) {
                    affectedEntities.get((Class<? extends ICharm>)aClass.getValue()).add(EntityType.valueOf(mob));
                }
            }

        }
    }

    public HashMap<String,Class<? extends ICharm>> getCharms() {
        return charms;
    }

    public String getDescription(Class<? extends ICharm> key) {
        return description.get(key);
    }

    public boolean affectedBlock(Class<? extends ICharm> key, Material material){
        return affectedBlocks.get(key).contains(material);
    }

    public boolean affectedEntity(Class<? extends ICharm> key, EntityType entityType){
        return affectedEntities.get(key).contains(entityType);
    }

    public ItemStack getItemStack(String key){
        return new ItemStack(item.get(key));
    }

}
