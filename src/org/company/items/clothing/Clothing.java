package com.company.items.clothing;

import com.company.items.Item;

public abstract class Clothing extends Item {
    private final int protection;

    public Clothing(String name, int weight, int value, int protection) {
        super(name, weight, value);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }
}
