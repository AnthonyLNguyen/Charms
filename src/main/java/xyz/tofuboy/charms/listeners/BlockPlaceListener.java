package xyz.tofuboy.charms.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;

/**
 * Created by antho on 10/2/2019.
 */
public class BlockPlaceListener implements Listener {
    private static Charms plugin;

    public BlockPlaceListener(Charms plugin){
        BlockPlaceListener.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event){
        Block placedBlock = event.getBlock();
        if (plugin.getCharmManager().isCharm(placedBlock)) {
            plugin.console(Charms.LogType.DEBUG, "Player " + event.getPlayer() + "placed a charm at location: " + placedBlock.getLocation());
            plugin.getCharmManager().createCharm(plugin.getCharmManager().getCharmFromHead((ItemStack) placedBlock.getDrops().toArray()[0]));
        }
    }
}
