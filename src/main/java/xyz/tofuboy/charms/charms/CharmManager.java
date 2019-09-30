package xyz.tofuboy.charms.charms;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.tofuboy.charms.Charms;
import xyz.tofuboy.charms.settings.CharmProperties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;

public class CharmManager {
    private HashMap<String, Class<? extends Charms>> validCharms = new HashMap<>();
    private HashMap<String, CharmProperties> charmProperties = new HashMap<>();

    public HashMap<String, Class<? extends Charms>> getValidCharms() {
        return this.validCharms;
    }

    public CharmProperties getCharmProperties(String var1) {
        Iterator var2 = this.charmProperties.keySet().iterator();

        String var3;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (String)var2.next();
        } while(!var3.equalsIgnoreCase(var1));

        return (CharmProperties) this.charmProperties.get(var3);
    }
}
