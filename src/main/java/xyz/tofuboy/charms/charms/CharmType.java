package xyz.tofuboy.charms.charms;

import xyz.tofuboy.charms.charms.types.Farming;

/**
 * Created by antho on 10/3/2019.
 */
public enum CharmType {
    FARMING(1, Farming.class);

    private final int id;
    private final Class<? extends Charm> data;

    CharmType(final int id, /*@NotNull*/ final Class<? extends Charm> data) {
        this.id = id;
        this.data = data;
    }

    public Class getCharmClass(){
        return this.data;
    }
}
