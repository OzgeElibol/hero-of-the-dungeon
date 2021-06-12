package org.company.characters;

import org.company.Door;
import org.company.Inventory;
import org.company.items.clothing.Clothing;
import org.company.items.weapons.Weapon;

public abstract class Character {
    private final String name;
    private Weapon weapon;
    private Clothing clothing;
    private int hitPoints;
    private boolean isDead;
    private final Inventory inventory;

    public Character(String name, Weapon weapon, Clothing clothing, int hitPoints, int inventorySize) {
        this.name = name;
        this.weapon = weapon;
        this.clothing = clothing;
        this.hitPoints = hitPoints;
        inventory = new Inventory(inventorySize);
    }

    public Character(String name, Weapon weapon, Clothing clothing, int hitPoints) {
        this.name = name;
        this.weapon = weapon;
        this.clothing = clothing;
        this.hitPoints = hitPoints;
        inventory = new Inventory(1000);
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public abstract void move(Door door);

    public void die() {
        setDead(true);
    }

    public boolean takeDamage(int damage) {
        if (hitPoints < 1) {
            die();
            return false;
        } else {
            hitPoints -= damage;
            return true;
        }
    }

    public abstract void attack(Character character);

    public abstract void block();

    public void display() {
        System.out.printf("%s %s, %d hit points, %s, %s%n", getClass().getSimpleName(), getName(), getHitPoints(), getWeapon().getName(), getClothing().getName());
    }
}
