package xyz.tofuboy.charms.settings;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.charms.Charm;
import xyz.tofuboy.charms.charms.CharmPlayer;
import xyz.tofuboy.charms.charms.CharmToLoad;
import xyz.tofuboy.charms.charms.CharmType;
import xyz.tofuboy.charms.charms.types.Farming;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by antho on 10/3/2019.
 */
public class CharmData extends DataFile{
    private long lastTimeSaved = System.currentTimeMillis();

    public CharmData(String fileName, CharmsPlugin plugin) {
        super(fileName, plugin);
    }

    public boolean isPlayerDataSaved(Player var1) {
        return this.getConfig().getStringList("Players." + this.getStorageIDForPlayer(var1)) != null;
    }

    public boolean isBlockCharm(Block block) {
        return this.getConfig().contains("BlockLocations." + block.getLocation());
    }

    public ArrayList<Location> getCharmLocationsFromUUID(UUID uuid) {
        ArrayList locations = new ArrayList();
        List stringList = this.getConfig().getStringList("Players." + uuid);
        if (stringList.size() > 0) {
            Iterator iter = stringList.iterator();

            while(iter.hasNext()) {
                String data = (String)iter.next();
                String[] splitData = data.split(",");
                locations.add(new Location(Bukkit.getWorld(splitData[1]), (double)Integer.valueOf(splitData[2]), (double)Integer.valueOf(splitData[3]), (double)Integer.valueOf(splitData[4])));
            }
        }

        return locations;
    }

    public boolean deleteCharmFromConfig(Location location) {
        String world = location.getWorld().getName();
        String x = String.valueOf(location.getBlockX());
        String y = String.valueOf(location.getBlockY());
        String z = String.valueOf(location.getBlockZ());
        Iterator iter = this.getConfig().getConfigurationSection("Players").getKeys(false).iterator();

        while(true) {
            String uuid;
            List stringList;
            do {
                if (!iter.hasNext()) {
                    return false;
                }

                uuid = (String)iter.next();
                stringList = this.getConfig().getStringList("Players." + uuid);
            } while(stringList.size() <= 0);

            Iterator iter2 = stringList.iterator();

            while(iter2.hasNext()) {
                String data = (String)iter2.next();
                String[] splitData = data.split(",");
                if (splitData[1].equals(world) && splitData[2].equals(x) && splitData[3].equals(y) && splitData[4].equals(z)) {
                    stringList.remove(data);
                    this.getConfig().set("Players." + uuid, stringList);
                    Player player = null;
                    Iterator iter3 = Bukkit.getOnlinePlayers().iterator();

                    while(iter3.hasNext()) {
                        Player player2 = (Player)iter3.next();
                        if (player2.getUniqueId().toString().equals(uuid)) {
                            player = player2;
                            break;
                        }
                    }

                    if (player != null && player.isOnline()) {
                        CharmPlayer charmPlayer = CharmsPlugin.getInstance().getCharmManager().getCharmPlayer(player);
                        charmPlayer.setTotalCharms(charmPlayer.getTotalCharms() - 1);
                        ArrayList list = (ArrayList)charmPlayer.getPlayersCharms().clone();
                        Iterator iter4 = list.iterator();

                        while(iter4.hasNext()) {
                            Charm charm = (Charm) iter4.next();
                            if (charm.getBlockLocation().equals(location)) {
                                CharmsPlugin.getInstance().getCharmManager().removeCharmFromActiveCharms(charm);
                                charmPlayer.getPlayersCharms().remove(charm);
                                break;
                            }
                        }
                    }

                    this.asyncSaveFile(true);
                    return true;
                }
            }
        }
    }

    public void saveCharmToPlayerFile(Player player, Charm charm) {
        String data = this.getCharmToString(charm);
        String storageID = this.getStorageIDForPlayer(player);
        Object stringList = this.getConfig().getStringList("Players." + storageID);
        if (stringList == null) {
            stringList = new ArrayList();
        }

        ((List)stringList).add(data);
        this.getConfig().set("Players." + storageID, stringList);
        this.asyncSaveFile(true);
    }

    public void removeCharmFromPlayerFile(Player player, Charm charm) {
        String[] splitData = this.getCharmToString(charm).split(",");
        String storageID = this.getStorageIDForPlayer(player);
        List stringList = this.getConfig().getStringList("Players." + storageID);
        if (stringList != null) {
            Iterator iter = stringList.iterator();

            while(iter.hasNext()) {
                String data = (String)iter.next();
                String[] split = data.split(",");
                if (split[0].equalsIgnoreCase(splitData[0]) && split[1].equalsIgnoreCase(splitData[1]) && split[2].equalsIgnoreCase(splitData[2]) && split[3].equalsIgnoreCase(splitData[3]) && split[4].equalsIgnoreCase(splitData[4])) {
                    stringList.remove(data);
                    break;
                }
            }

            this.getConfig().set("Players." + storageID, stringList);
            this.asyncSaveFile(true);
        }
    }

    public ArrayList<Charm> loadPlayerCharms(CharmPlayer charmPlayer) {
        Player player = charmPlayer.getPlayer();
        int totalCharms = 0;
        String storageID = this.getStorageIDForPlayer(player);
        List stringList = this.getConfig().getStringList("Players." + storageID);
        if (stringList != null && stringList.size() != 0) {
            ArrayList list = new ArrayList();
            Iterator iter = stringList.iterator();

            while(true) {
                while(true) {
                    while(true) {
                        CharmToLoad charmToLoad;
                        do {
                            if (!iter.hasNext()) {
                                charmPlayer.setTotalCharms(totalCharms);
                                return list;
                            }

                            String var8 = (String)iter.next();
                            charmToLoad = this.getCharmFromString(player, var8);
                        } while(charmToLoad == null);

                        if (charmToLoad.getLocation().getChunk().isLoaded()) {
                            Block head = charmToLoad.getLocation().getBlock();
                            if (head.getType() == Material.PLAYER_HEAD && charmToLoad.isLocationOnBlock(head.getLocation())){
                                Block b = (Block)head;
                                CharmType type = CharmsPlugin.getInstance().getCharmManager().getCharmFromHead(head);
                                Class clazz = type.getCharmClass();
                                Charm newCharm = null;

                                try {
                                    Constructor c = clazz.getDeclaredConstructor(Player.class, Block.class, CharmType.class);
                                    newCharm = (Charm)c.newInstance();
                                } catch (InstantiationException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalAccessException e2) {
                                    e2.printStackTrace();
                                } catch (InvocationTargetException e3) {
                                    e3.printStackTrace();
                                } catch (NoSuchMethodException e4) {
                                    e4.printStackTrace();
                                }

                                if (newCharm != null) {
                                    charmPlayer.getPlayersCharms().add(newCharm);
                                    newCharm.onLoad();
                                    CharmsPlugin.getInstance().getCharmManager().addCharmToActiveCharms(newCharm);
                                    list.add(newCharm);
                                    ++totalCharms;
                                    break;
                                }
                                System.out.println("An error has occurred while trying to load " + player.getName() + " Charm (Reference for Dev - Charm null)");
                            }
                        } else {
                            ++totalCharms;
                            CharmsPlugin.getInstance().getCharmManager().getCharmsToLoad().add(charmToLoad);
                        }
                    }
                }
            }
        } else {
            charmPlayer.setTotalCharms(totalCharms);
            return null;
        }
    }

    private CharmToLoad getCharmFromString(Player player, String data) {
        String[] splitData = data.split(",");
        World world = Bukkit.getWorld(splitData[1]);
        if (world == null) {
            return null;
        } else {
            return new CharmToLoad(splitData[0], world, Integer.valueOf(splitData[2]), Integer.valueOf(splitData[3]), Integer.valueOf(splitData[4]), player);
        }
    }

    private String getStorageIDForPlayer(Player player) {
        //return CharmsPlugin.getInstance().getProperties().useUUIDS() ? player.getUniqueId().toString() : player.getName();
        return player.getUniqueId().toString();
    }

    private String getCharmToString(Charm charm) {
        String identifier = charm.getIdentifier();
        String world = charm.getBlockLocation().getWorld().getName();
        int x = charm.getBlockLocation().getBlockX();
        int y = charm.getBlockLocation().getBlockY();
        int z = charm.getBlockLocation().getBlockZ();

        return identifier + "," + world + "," + x + "," + y + "," + z;
    }

    private void asyncSaveFile(boolean var1) {
        if (var1 || System.currentTimeMillis() - this.lastTimeSaved > 20000L) {
            this.lastTimeSaved = System.currentTimeMillis();
            super.saveFile();
        }
    }
}
