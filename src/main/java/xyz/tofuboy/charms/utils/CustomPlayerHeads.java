package xyz.tofuboy.charms.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class CustomPlayerHeads {


    private static ItemStack getSkull(){
        ItemStack skull;
        if (getVersion() > 12) {
            skull = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } else {
            skull = new ItemStack(Objects.requireNonNull(Material.getMaterial("SKULL_ITEM")), 1, (short) SkullType.PLAYER.ordinal());
        }

        return skull;
    }

    public static ItemStack getHead(String value) {
        ItemStack skull = getSkull();
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }


    private static int getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        name = (name.substring(name.lastIndexOf('.') + 1) + ".").substring(3);
        return Integer.parseInt(name.substring(0, name.length() - 4));
    }
}
