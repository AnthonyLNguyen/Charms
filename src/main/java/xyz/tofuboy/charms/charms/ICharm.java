package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import xyz.tofuboy.charms.Charms;

public interface ICharm {

    String name();

    String headID();

    String getDescription();

    String getHeadID();

    void doAbility(Location location, int radius);

}
