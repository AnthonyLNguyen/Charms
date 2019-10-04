package xyz.tofuboy.charms.charms;

/**
 * Created by antho on 10/3/2019.
 */
public enum CharmType {
    FARMING(1, Charm.class);

    private final int id;
    public final Class<? extends Charm> data;

    private CharmType(final int id, /*@NotNull*/ final Class<? extends Charm> data) {
        this.id = id;
        this.data = data;
    }

    public Class getCharmClass(){
        return this.data;
    }
}
