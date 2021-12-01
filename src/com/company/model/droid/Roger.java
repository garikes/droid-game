package com.company.model.droid;

public class Roger extends Droid {

    public Roger(String name) {
        super(name);
    }

    @Override
    public int getTotalDamage() {
        return 0;
    }

    @Override
    public int defend(int damage) {
        return 0;
    }

}
