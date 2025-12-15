package net.tuffetspider.xiphosura.common.entity;

import java.util.Arrays;
import java.util.Comparator;

// Enum defining variants
// TODO: data-driven or StringIdentifiable
public enum HorseshoeCrabVariant {
    DEFAULT(0),
    BLUE(1),
    TAN(2),
    BLACK(3),
    WHITE(4);

    private static final HorseshoeCrabVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(HorseshoeCrabVariant::getId)).toArray(HorseshoeCrabVariant[]::new);
    private final int id;

    HorseshoeCrabVariant(int id){
        this.id =id;
    }
    public int getId(){
        return this.id;
    }

    public static HorseshoeCrabVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
