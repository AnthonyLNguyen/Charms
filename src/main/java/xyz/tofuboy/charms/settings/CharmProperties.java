package xyz.tofuboy.charms.settings;

import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;

public class CharmProperties {
    private String identifier;
    private ItemStack displayItem;

    public CharmProperties(String identifier, ItemStack var11) {
        this.identifier = identifier;
        this.displayItem = var11;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    public ItemStack getItemStack() {
        return this.displayItem;
    }

}
