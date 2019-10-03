package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;

public interface ICharm {

    String name();

    ItemStack head();

    String getDescription();

    String getHeadID();

    void doAbility(Location location, int radius);

}
