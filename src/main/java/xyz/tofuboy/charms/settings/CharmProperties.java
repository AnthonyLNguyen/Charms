package xyz.tofuboy.charms.settings;

import com.deanveloper.skullcreator.SkullCreator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.charms.ICharm;
import xyz.tofuboy.charms.charms.types.Farming;

import java.util.*;

public class CharmProperties extends DataFile {
    public HashMap<String,Class<? extends ICharm>> charms = new HashMap<>();
    private HashMap<String,String> name = new HashMap<>();


    private HashMap<String,String> headID = new HashMap<>();
    private HashMap<String,String> description = new HashMap<>();
    private HashMap<String,Material> item = new HashMap<>();
    private HashMap<String,List<Material>> affectedBlocks = new HashMap<>();
    private HashMap<String,List<EntityType>> affectedEntities = new HashMap<>();

    public CharmProperties(String fileName, Charms plugin) {
        super(fileName, plugin);
        this.loadProperties();
    }

    public void loadProperties() {
        String mainPath = "Charms.";
        String path;


        charms.put("FARMER",Farming.class);


        for (String key : charms.keySet()) {
            path = mainPath + key;
            try {
                item.put(key, Material.getMaterial(this.getConfig().getString(path + ".item")));
            } catch (NullPointerException e){
                item.put(key, Material.CREEPER_HEAD);
            }

            affectedBlocks.put(key,new ArrayList<Material>());
            affectedEntities.put(key,new ArrayList<EntityType>());

            name.put(key, (this.getConfig().contains(charms.get(key) + ".name")) ? this.getConfig().getString(path + ".name") : "NO_NAME");
            headID.put(key, (this.getConfig().contains(charms.get(key) + ".headBASE64")) ? this.getConfig().getString(path + ".headBASE64") : "notch");
            description.put(key, (this.getConfig().contains(charms.get(key) + ".description")) ? this.getConfig().getString(path + ".description") : "NO_DESCRIPTION");

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

    public HashMap<String,Class<? extends ICharm>> getCharms() {
        return charms;
    }

    public String getHeadID(String key) {
        return headID.get(key);
    }
    public String getDescription(String key) {
        return description.get(key);
    }

    public boolean isAffectedBlock(String key, Material material){
        return affectedBlocks.get(key).contains(material);
    }

    public boolean isAffectedEntity(String key, EntityType entityType){
        return affectedEntities.get(key).contains(entityType);
    }

    public boolean isCharm(Block block){
        if (block.getBlockData() instanceof SkullMeta) {
            final SkullMeta meta = (SkullMeta) block.getBlockData();
            return headID.containsValue(meta.toString());
        } else return false;
    }

    public ItemStack getItemStack(String key){
        //String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L" +
        //        "3RleHR1cmUvNTIyODRlMTMyYmZkNjU5YmM2YWRhNDk3YzRmYTMwOTRjZDkzMjMxYTZiNTA1YTEyY2U3Y2Q1MTM1YmE4ZmY5MyJ9fX0=";
        String base64 = getHeadID(key);
        return SkullCreator.fromBase64(SkullCreator.Type.ITEM,base64);
    }

}
