package com.company.model.battle;

import com.company.model.droid.Droid;
import com.company.model.droid.Medic;

import java.io.FileWriter;

import static com.company.utilities.ConsoleColors.*;

public class Duel extends Battle {

    private String msg;

    public Duel(Droid attacker, Droid defender) {
        super(attacker, defender);
    }

    @Override
    public void fight() {

        int round = 1;

        while (Droid.isDroidAlive(attacker) && Droid.isDroidAlive(defender)) {
            int handleShot = attacker.getTotalDamage();

            int rejected = defender.defend(handleShot);
            defender.setHealth(-handleShot + rejected);


            msg += BLACK_BOLD + "<---------- ROUND " + round++ + " --------->\n" +
                    BLACK_BOLD_BRIGHT + attacker.getName() + " damaged " + defender.getName() + " with " + RED_BOLD_BRIGHT + "-" + handleShot + BLACK_BOLD_BRIGHT +" points.\n" +
                    defender.getName() + " managed to reject " + GREEN_BOLD_BRIGHT + "+" + rejected + BLACK_BOLD_BRIGHT + " points.\n" +
                    defender.getName() + "'s current health is " + defender.getHealth() + "\n";

            if (attacker instanceof Medic) {
                int treated = ((Medic) attacker).treat(attacker);
                msg += "" + attacker.getName() + GREEN_BOLD_BRIGHT + " +" + treated + "\n" + BLACK_BOLD_BRIGHT;
            }

            swap();
        }

        if (Droid.isDroidAlive(attacker))
            msg += YELLOW_BOLD + attacker.getName() + " WINS!\n";
        else
            msg += YELLOW_BOLD + defender.getName() + " WINS!\n";

        // recover droids
        attacker.setHealth(1000);
        defender.setHealth(1000);
    }

    public String getMsg() {
        return msg;
    }

}

