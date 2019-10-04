package xyz.tofuboy.charms.charms.types;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import xyz.tofuboy.charms.CharmsPlugin;
import xyz.tofuboy.charms.charms.Charm;
import xyz.tofuboy.charms.charms.CharmType;
import xyz.tofuboy.charms.utils.BlockDetection;

import java.util.List;

public class Farming extends Charm {

    Farming (){
        super();
    }

    public String getIdentifier() {
        return "Farming";
    }

    public void doAbility(Location location, int radius) {
        BlockDetection bd = new BlockDetection();
        List<Block> blocks = bd.getBlocksFromLocation(location, radius);
        for (Block b:
             blocks) {
            if (b instanceof Ageable && CharmsPlugin.getInstance().getCharmManager().isAffectedBlock(getIdentifier(), ((Ageable) b).getMaterial())){
                Ageable a = (Ageable)b;
                if (a.getAge() < a.getMaximumAge())
                    a.setAge(a.getAge());
            }
        }
    }

    public CharmType getType(){
        return CharmType.FARMING;
    }
}
