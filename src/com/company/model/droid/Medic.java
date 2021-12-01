package com.company.model.droid;

import java.util.Random;

public class Medic extends Droid {

    public static final int MAXIMUM_DAMAGE = 125;

    public Medic(String name) {
        super(name);
    }

    @Override
    public int getTotalDamage() {
        return random.nextInt(MAXIMUM_DAMAGE);
    }

    @Override
    public int defend(int damage) {
        return random.nextInt((int) (MAXIMUM_DAMAGE * 0.1));
    }

    public static int treat(Droid droid) {
        int treated = new Random().nextInt((int) (Droid.TOTAL_HEALTH * 0.1));

        droid.setHealth(treated);
        return treated;
    }
}
