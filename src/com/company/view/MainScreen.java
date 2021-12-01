package com.company.view;

import com.company.model.droid.Droid;
import com.company.model.droid.DroidEnum;

import java.util.List;

import static com.company.utilities.ConsoleColors.*;

public class MainScreen {

    static  {
        System.out.println(WHITE_BACKGROUND_BRIGHT + BLACK_BOLD +  "             WELCOME TO DROID GAME! GOOD LUCK!");
        System.out.println("                    MENU SELECTION");
    }

    public void showSelectionMenu() {
        System.out.println(BLACK_BOLD + "1. CREATE DROID");
        System.out.println("2. SHOW LIST OF DROIDS");
        System.out.println("3. RUN DUEL BATTLE");
        System.out.println("4. RUN TEAM FIGHT");
        System.out.println("5. SAVE BATTLE RESULTS IN FILE");
        System.out.println("6. READ BATTLE RESULTS FROM FILE");
        System.out.println("0. EXIT");
    }

    public void showDroids() {
        DroidEnum[] names = DroidEnum.values();

        for (int i = 0; i < names.length; ++i)
            System.out.println((i + 1) + ". " + names[i]);

        System.out.println("0. BACK");

    }

    public void showDroids(List<Droid> droids) {
        System.out.println("<------------------------------------------>");
        int len = droids.size();
        for (int i = 0; i < len; ++i) {
            Droid droid = droids.get(i);
            System.out.println(BLACK_BOLD + (i + 1) + ". " + droid.getClassName() + " -> " + droid.getName());
        }
    }

    public void drawLine() {
        System.out.println(BLACK_BOLD + "<------------------------------------------>");
    }

}
