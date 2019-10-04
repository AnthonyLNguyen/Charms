package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;

public abstract class Charm implements ICharm{
    private CharmType charmType;
    private Location blockLocation;
    private Block block;
    private Player player;

    protected Charm (Player player, Block block, CharmType charmType){
        this.player = player;
        this.block = block;
        this.charmType = charmType;
        this.blockLocation = block.getLocation();
    }

    public ItemStack head() { return CharmsPlugin.getInstance().getCharmManager().getHead(getIdentifier()); }

    public String getDescription() {
        return CharmsPlugin.getInstance().getCharmManager().getDescription(getIdentifier());
    }

    public ItemStack getHead() {
        return CharmsPlugin.getInstance().getCharmManager().getHead(getIdentifier());
    }

    public void doAbility(Location location, int radius) {

    }

    public Location getBlockLocation(){
        return blockLocation;
    }

    public Player getPlayer() { return player; }
}
