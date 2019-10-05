package xyz.tofuboy.charms.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.CharmsPlugin;

/**
 * Created by antho on 10/2/2019.
 */
public class CharmPlaceListener implements Listener {

    public CharmPlaceListener(){
    }

    @EventHandler
    public void onCharmPlace (BlockPlaceEvent event){
        Block placedBlock = event.getBlock();
        if (CharmsPlugin.getInstance().getCharmManager().isCharm(placedBlock)) {
            CharmsPlugin.getInstance().console(CharmsPlugin.LogType.DEBUG, "Player " + event.getPlayer() + "placed a charm at location: " + placedBlock.getLocation());
            CharmsPlugin.getInstance().getCharmManager().createBlockCharm(CharmsPlugin.getInstance().getCharmManager().getCharmFromHead(event.getBlock()),event.getBlock(),event.getPlayer());
        }
    }
}
