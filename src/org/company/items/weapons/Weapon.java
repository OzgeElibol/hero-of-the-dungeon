package org.company.items.weapons;

import org.company.items.Item;

public abstract class Weapon extends Item {
    private final int damage;
    private final int range;

    public Weapon(String name, int weight, int value, int damage, int range) {
        super(name, weight, value);
        this.damage = damage;
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
<<<<<<< HEAD

=======
   
>>>>>>> 664c37df7e9f19dd29c3ed92a79cdbdc53608255
    @Override
    public String toString() {
        return String.format("%s, %d damage, %d range", super.toString(), damage, range);
    }
}
