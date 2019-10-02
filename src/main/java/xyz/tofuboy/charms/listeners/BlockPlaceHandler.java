package xyz.tofuboy.charms.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.tofuboy.charms.Charms;

/**
 * Created by antho on 10/2/2019.
 */
public class BlockPlaceHandler implements Listener {
    static Charms plugin;

    BlockPlaceHandler(Charms plugin){
        BlockPlaceHandler.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event){
        Block placedBlock = event.getBlock();
        if (plugin.getCharmProperties().isCharm(placedBlock))
            plugin.console(Charms.LogType.DEBUG,"Player placed a charm at location: " + placedBlock.getLocation());
    }
}
