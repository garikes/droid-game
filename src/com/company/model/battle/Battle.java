package com.company.model.battle;

import com.company.model.droid.Droid;

abstract public class Battle {

    protected Droid attacker;
    protected Droid defender;

    Battle(Droid attacker, Droid defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    protected void swap() {
        Droid droid = attacker;
        attacker = defender;
        defender = droid;
    }

    abstract public void fight();
}
