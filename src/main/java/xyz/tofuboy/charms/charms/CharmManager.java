package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.settings.CharmProperties;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CharmManager {
    private CharmProperties prop;
    private CharmsPlugin plugin;
    private List<CharmPlayer> charmPlayers = new ArrayList();
    private ConcurrentHashMap<String, ArrayList<CharmType>> activeCharms = new ConcurrentHashMap<String, ArrayList<CharmType>>();
    private ArrayList<CharmToLoad> charmsToLoad = new ArrayList();;

    public CharmManager(CharmProperties charmProperties, CharmsPlugin plugin) {
        this.prop = charmProperties;
        this.plugin = plugin;
    }

    public CharmProperties getCharmProperties() {
        return prop;
    }

    public void createBlockCharm(CharmType charmType,Block block, Player player){
        plugin.console(CharmsPlugin.LogType.DEBUG,"Creating charm " + charmType);
        //plugin.getCharmData().writeLocation(charmType,block.getLocation(),player);
    }


    public CharmType getCharmFromHead(Block placedBlock){
        ItemStack item = (ItemStack) placedBlock.getDrops().toArray()[0];
        for (Map.Entry<String,ItemStack> s : prop.getAllHeads().entrySet()) {
            if (s.getValue().equals(item)){
                return prop.getAllCharms().get(s.getKey());
            }
        }
        return null;
    }

    public boolean isValidCharmName(String text){
        text = text.toLowerCase();
        return (prop.getAllCharms().containsKey(text));
    }

    public HashMap<String, CharmType> getValidCharms() {
        return prop.getAllCharms();
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

    public List<CharmPlayer> getCharmPlayers(Player player) {
        return this.charmPlayers;
    }

    public CharmPlayer getCharmPlayer(Player player) {
        Iterator iter = this.charmPlayers.iterator();

        CharmPlayer charmPlayer;
        do {
            if (!iter.hasNext()) {
                return null;
            }

            charmPlayer = (CharmPlayer)iter.next();
        } while(!charmPlayer.getPlayer().getUniqueId().equals(player.getUniqueId()));

        return charmPlayer;
    }

    public void removeCharmFromActiveCharms(Charm charm) {
            if (charm != null) {
                ArrayList list = (ArrayList)this.getActiveCharms().get(charm.getIdentifier());
                if (list != null) {
                    list.remove(charm);
                    this.getActiveCharms().put(charm.getIdentifier(), list);
                }
            }
        }
    public ConcurrentHashMap<String, ArrayList<CharmType>> getActiveCharms() {
        return this.activeCharms;
    }

    public ArrayList<CharmToLoad> getCharmsToLoad() {
        return this.charmsToLoad;
    }

    public void addCharmToActiveCharms(Charm charm) {
        ArrayList list = (ArrayList)this.getActiveCharms().get(charm.getIdentifier());
        if (list == null) {
            list = new ArrayList();
        }

        list.add(charm);
        this.getActiveCharms().put(charm.getIdentifier(), list);
    }
}
