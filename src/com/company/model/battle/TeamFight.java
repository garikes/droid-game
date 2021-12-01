package com.company.model.battle;

import com.company.model.droid.Droid;
import com.company.model.droid.Medic;
import com.company.model.team.Team;
import com.company.view.MainScreen;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.company.model.droid.Droid.isDroidAlive;
import static com.company.utilities.ConsoleColors.*;

public class TeamFight extends Battle {

    Scanner in = new Scanner(System.in);

    public TeamFight(Team attacker, Team defender) {
        super(attacker, defender);

    }

    @Override
    public void fight() {

        int round = 1;

        while (isDroidAlive(attacker) && isDroidAlive(defender)) {

            System.out.println(BLACK_BOLD + "<---------- ROUND " + round++ + " --------->");
            System.out.println(attacker.getName() + ". ( " + ((Team) attacker).getTeam().size() + " droid(s) )");
            System.out.println(defender.getName() + ". ( " + ((Team) defender).getTeam().size() + " droid(s) )");

            System.out.println(BLACK_BOLD + attacker.getName() + " ATTACKS. " + BLACK_BOLD_BRIGHT + "\nChoose an enemy droid to attack: ");
            int size = ((Team) defender).getTeam().size();
            int idx;

            while (true) {
                idx = in.nextInt();

                if (idx > 0 || idx <= size)
                    break;

                System.out.println(RED_BOLD + "TRY AGAIN!!!" + BLACK_BOLD_BRIGHT);
            }

            int handleShot = attacker.getTotalDamage();
            int rejected = defender.defend(handleShot);

            shuffleTeam((Team) defender);
            defeatEnemy(--idx, handleShot, rejected);

            if (((Team) attacker).isTreatable()) {
                int treated = ((Team) attacker).getTeam().stream().mapToInt(Medic::treat).sum();
                System.out.println("" + attacker.getName() + GREEN_BOLD_BRIGHT + " +" + treated + "\n" + BLACK_BOLD_BRIGHT);
            }

            if (!isDroidAlive(((Team) defender).getTeam().get(idx))) {
                System.out.println("You have destroyed an enemy droid! \n" +  (((Team) defender).getTeam().size() - 1) + " droid(s) left!");
                ((Team) defender).getTeam().remove(idx);
            }

            swap();
        }

        if (isDroidAlive(attacker))
            System.out.println(YELLOW_BOLD + attacker.getName() + " WINS!");
        else
            System.out.println(YELLOW_BOLD + defender.getName() + " WINS!");
    }

    private void defeatEnemy(int idx, int damage, int rejected) {
        List<Droid> droids = ((Team) defender).getTeam();

        Droid target = ((Team) defender).getTeam().get(idx);
        target.setHealth(-damage + rejected);

        System.out.println(BLACK_BOLD_BRIGHT + attacker.getName() + " damaged " + defender.getName() + " with " + RED_BOLD_BRIGHT + "-" + damage + BLACK_BOLD_BRIGHT +" points.");
        System.out.println(defender.getName() + " managed to reject " + GREEN_BOLD_BRIGHT + "+" + rejected + BLACK_BOLD_BRIGHT + " points.");

        int splash = (damage - rejected) - target.getHealth();
        if (splash <= 0 || ((Team) defender).getTeam().size() == 1)
        {
            ((Team) defender).adjustHealth();
            System.out.println(defender.getName() + "'s current health is " + defender.getHealth());
            return;
        }

        if (idx == 0) {
            Droid right = droids.get(idx + 1);
            right.setHealth(-splash);
            System.out.println("Right droid " + right.getName() + " was attacked with " + splash + " points.");
        } else if (idx == droids.size() - 1) {
            Droid left = droids.get(idx - 1);
            left.setHealth(-splash);
            System.out.println("Left droid " + left.getName() + " was attacked with " + splash + " points.");
        } else {
            Droid left = droids.get(idx - 1);
            Droid right = droids.get(idx + 1);

            splash /= 2;

            left.setHealth(-splash);
            System.out.println("Left droid " + left.getName() + " was attacked with " + splash + " points.");
            right.setHealth(-splash);
            System.out.println("Right droid " + right.getName() + " was attacked with " + splash+ " points.");
        }

        ((Team) defender).adjustHealth();
        System.out.println(defender.getName() + "`s current health is " + defender.getHealth());
    }

    private void shuffleTeam(Team team) {
        Collections.shuffle(team.getTeam());
    }


}
