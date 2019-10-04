package xyz.tofuboy.charms.charms;

/**
 * Created by antho on 10/3/2019.
 */
public enum CharmType {
    FARMING(1, Charm.class);

    private final int id;
    public final Class<?> data;

    private CharmType(final int id, /*@NotNull*/ final Class<?> data) {
        this.id = id;
        this.data = data;
    }
}
