package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;

public abstract class Charm implements ICharm{
    private ICharm charmType;
    private Location blockLocation;
    private Material block;
    private int level;
    private int cooldown;
    private String name;

    protected Charm (){

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
}
