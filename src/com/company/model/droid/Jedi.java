package com.company.model.droid;

public class Jedi extends Droid {

    public static final int MAXIMUM_DAMAGE = 200;

    public Jedi(String name) {
        super(name);
    }

    @Override
    public int getTotalDamage() {
        int damage = random.nextInt(MAXIMUM_DAMAGE);

        return damage;
    }

    @Override
    public int defend(int damage) {
        int rejected = random.nextInt((int) (MAXIMUM_DAMAGE * 0.1));

        return rejected;
    }

}
