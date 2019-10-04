package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface ICharm {

    String getIdentifier();

    ItemStack head();

    String getDescription();

    ItemStack getHead();

    void doAbility(Location location, int radius);

    CharmType getType();

}
