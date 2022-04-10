package com.nad2040.redstone_ext.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum Endianness implements StringIdentifiable {
    LITTLE("little"),
    BIG("big");

    private final String name;

    private Endianness(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}