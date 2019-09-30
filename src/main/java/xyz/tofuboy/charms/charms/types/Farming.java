package xyz.tofuboy.charms.charms.types;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.charms.ICharm;
import xyz.tofuboy.charms.utils.BlockDetection;

import java.util.List;

public class Farming implements ICharm {

    Charms plugin;

    Farming (Charms plugin){
        this.plugin = plugin;
    }

    public String name() {
        return plugin.getCharmProperties().getName().get(this.getClass());
    }

    public String getDescription() {
        return plugin.getCharmProperties().getDescription(this.getClass());
    }

    public String getHeadID() {
        return null;
    }

    public void doAbility(Location location, int radius) {
        BlockDetection bd = new BlockDetection();
        List<Block> blocks = bd.getBlocksFromLocation(location, radius);
        for (Block b:
             blocks) {
            if (b instanceof Ageable && plugin.getCharmProperties().affectedBlock(this.getClass(), ((Ageable) b).getMaterial())){
                Ageable a = (Ageable)b;
                if (a.getAge() < a.getMaximumAge())
                    a.setAge(a.getAge());
            }
        }
    }
}
