package xyz.tofuboy.charms.charms;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CharmToLoad {

    private final String type;
    private final World world;
    private final Integer x;
    private final Integer y;
    private final Integer z;
    private Location location;
    private Player player;

    public CharmToLoad(String type, World world, Integer x, Integer y, Integer z, Player player) {
        this.type = type;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
        this.location = new Location(world, (double)x, (double)y, (double)z);
    }

    public String getIdentifier() {
        return type;
    }

    public World getWorld() {
        return world;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public Location getLocation() {
        return this.location;
    }

    public Player getPlayer() {
        return this.player;
    }

}
