package entity;

import java.util.Random;

/**
 * Created by Chris on 10/4/2015.
 */
public class Entity {
    private String name;
    private int health;
    private int maxHealth;
    private int strength;
    private int dexterity;
    private int intellect;
    private static Random rand = new Random();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int attack(Entity target) {
        if (target.dexterity + rand.nextInt(5) > dexterity + rand.nextInt(5)) {
            return -1;
        }
        int damage = strength + rand.nextInt(5);
        target.setHealth(target.getHealth()  - damage);
        return damage;
    }

    public Entity(String name, int health, int maxHealth, int strength, int dexterity, int intellect) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intellect = intellect;
    }
}
