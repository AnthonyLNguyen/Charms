package xyz.tofuboy.charms.charms;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.settings.CharmProperties;

import java.util.Map;

public class CharmManager {
    private CharmProperties prop;
    private CharmsPlugin plugin;

    public CharmManager(CharmProperties charmProperties, CharmsPlugin plugin) {
        this.prop = charmProperties;
        this.plugin = plugin;
    }

    public CharmProperties getCharmProperties() {
        return prop;
    }

    public void createBlockCharm(Charm charm){
        plugin.console(CharmsPlugin.LogType.DEBUG,"Creating charm " + charm.getIdentifier());
        plugin.getCharmData().writeLocation(charm.getType(),charm.getBlockLocation());
    }

    public String getCharmFromHead(ItemStack head){
        for (Map.Entry<String,ItemStack> s : prop.getAllHeads().entrySet()) {
            if (s.getValue().equals(head)){
                return s.getKey();
            }
        }
        return "";
    }

    public boolean isValidCharmName(String text){
        text = text.toLowerCase();
        return (prop.getAllCharms().containsKey(text));
    }

    public ItemStack getHead(String key) {
        return prop.getAllHeads().get(key.toLowerCase());
    }

    public String getDescription(String key) {
        return prop.getAllDescriptions().get(key.toLowerCase());
    }

    public boolean isAffectedBlock(String key, Material material){
        return prop.getAllBlocks().get(key.toLowerCase()).contains(material);
    }

    public boolean isAffectedEntity(String key, EntityType entityType){
        return prop.getAllEntities().get(key.toLowerCase()).contains(entityType);
    }

    public boolean isCharm(Block block){
        if (block.getType().equals(Material.PLAYER_HEAD)) {
            return prop.getAllHeads().containsValue(block.getDrops().toArray()[0]);
        } else return false;
    }

    public ItemStack getItemStack(String key){
        return prop.getAllHeads().get(key.toLowerCase());
    }
}
