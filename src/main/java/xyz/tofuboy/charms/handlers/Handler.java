package xyz.tofuboy.charms.handlers;

import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.tofuboy.charms.Charms;

/**
 * Created by antho on 10/2/2019.
 */
public class Handler implements Listener {
    static Charms plugin;

    Handler(Charms plugin){
        Handler.plugin = plugin;
    }

    public void onBlockPlace (BlockPlaceEvent event){
        Block placedBlock = event.getBlock();
        plugin.getCharmProperties().isCharm(placedBlock);
    }
}
