package com.company.model.droid;

import java.util.Random;

abstract public class Droid {

    private String name;

    public static final int TOTAL_HEALTH = 1000;
    protected int health;

    public Random random = new Random();

    public Droid(String name) {
        this.name = name;

        health = TOTAL_HEALTH;
    }

    abstract public int getTotalDamage();
    abstract public int defend(int damage);

    public static boolean isDroidAlive(Droid droid) {
        return (droid.health == 0) ? false : true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health += health;

        if (this.health < 0)
            this.health = 0;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
