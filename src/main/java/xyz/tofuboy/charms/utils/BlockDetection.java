package xyz.tofuboy.charms.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

/**
 * Created by antho on 9/30/2019.
 */
public class BlockDetection {

    public ArrayList<Block> getBlocksFromLocation(Location start, int radius){
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(double x = start.getX() - radius; x <= start.getX() + radius; x++){
            for(double y = start.getY() - radius; y <= start.getY() + radius; y++){
                for(double z = start.getZ() - radius; z <= start.getZ() + radius; z++){
                    Location loc = new Location(start.getWorld(), x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }
}
