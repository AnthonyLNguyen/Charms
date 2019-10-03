package xyz.tofuboy.charms.charms;

import com.sun.jndi.ldap.EntryChangeResponseControl;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.settings.CharmProperties;
import xyz.tofuboy.charms.utils.CustomPlayerHeads;

import java.util.Map;
import java.util.Set;

public class CharmManager {
    private CharmProperties prop;
    private Charms plugin;

    public CharmManager(CharmProperties charmProperties, Charms plugin) {
        this.prop = charmProperties;
        this.plugin = plugin;
    }

    public CharmProperties getCharmProperties() {
        return prop;
    }

    public void createCharm(String charmName){
        plugin.console(Charms.LogType.DEBUG,"Creating charm " + charmName);
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
