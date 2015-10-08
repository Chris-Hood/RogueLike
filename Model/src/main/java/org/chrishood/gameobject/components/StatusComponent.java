package org.chrishood.gameobject.components;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.GameObjectMessage;

/**
 * Created by Chris on 10/6/2015.
 */
public class StatusComponent implements IGameComponent {
    private GameObject owner;
    private int health;
    private int maxHealth;
    private int strength;
    private int dexterity;
    private int intellect;

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

    public StatusComponent(GameObject owner, int health, int maxHealth, int strength, int dexterity, int intellect) {
        this.owner = owner;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intellect = intellect;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public GameObject getGameObject() {
        return owner;
    }

    @Override
    public void receiveMessage(String topic, GameObjectMessage message) {

    }

    public void test() {
        owner.broadcast("thing", new GameObjectMessage<String>("stuff", this));
    }
}
