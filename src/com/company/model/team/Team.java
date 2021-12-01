package com.company.model.team;

import com.company.model.droid.Droid;
import com.company.model.droid.Medic;

import java.util.List;

public class Team extends Droid {

    private final List<Droid> team;
    private boolean treatable;

    public Team(String name, List<Droid> team) {
        super(name);
        this.team = team;

        for  (Droid droid: team)
            if (droid instanceof Medic) {
                treatable = true;
                break;
            }
    }

    public void adjustHealth() {
        health = team.stream().mapToInt(Droid::getHealth).sum();
    }

    @Override
    public int getTotalDamage() {
        int total = team.stream().mapToInt(Droid::getTotalDamage).sum();
        return total;
    }

    @Override
    public int defend(int damage) {
        int rejected = team.stream().mapToInt(droid -> droid.defend(damage)).sum();

        return rejected;
    }

    public List<Droid> getTeam() {
        return team;
    }

    public boolean isTreatable() {
        return treatable;
    }
}

