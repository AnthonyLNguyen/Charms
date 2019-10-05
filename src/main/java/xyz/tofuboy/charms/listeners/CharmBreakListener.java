package xyz.tofuboy.charms.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.tofuboy.charms.CharmsPlugin;

/**
 * Created by antho on 10/2/2019.
 */
public class CharmBreakListener implements Listener {

    public CharmBreakListener(){
    }

    @EventHandler
    public void onCharmBreak (BlockBreakEvent event){
        Block placedBlock = event.getBlock();
        if (CharmsPlugin.getInstance().getCharmManager().isCharm(placedBlock)) {
            CharmsPlugin.getInstance().console(CharmsPlugin.LogType.DEBUG, "Player " + event.getPlayer() + "broke a charm at location: " + placedBlock.getLocation());
            CharmsPlugin.getInstance().getCharmManager().breakBlockCharm(CharmsPlugin.getInstance().getCharmManager().getCharmFromHead(event.getBlock()),event.getBlock(),event.getPlayer());
        }
    }
}
