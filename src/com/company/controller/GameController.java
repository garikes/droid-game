package com.company.controller;

import com.company.model.battle.Battle;
import com.company.model.battle.Duel;
import com.company.model.battle.TeamFight;
import com.company.model.droid.*;
import com.company.model.team.Team;
import com.company.view.MainScreen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.utilities.ConsoleColors.*;

public class GameController {

    private MainScreen screen;

    private List<Droid> droids;
    private Battle battle;

    Scanner in = new Scanner(System.in);

    FileWriter fileWriter;
    boolean write;

    public GameController() throws IOException {
        screen = new MainScreen();
        droids = new ArrayList<>();

        droids.add(new Jedi("Billy Herrington"));
        droids.add(new Medic("Van Darkholme"));
        droids.add(new Medic("Ricardo Milos"));
        droids.add(new Jedi("Danny Lese"));
        droids.add(new Jedi("Bogdan"));

        render();
    }

    private void render() throws IOException {
        while (true) {
            screen.showSelectionMenu();

            switch (in.nextInt()) {
                case 1 -> createDroid();
                case 2 -> showDroidsList();
                case 3 ->runDuel();
                case 4 ->runTeamFight();
                case 5 -> writeBattle();
                case 6 -> readBattle();
                default -> System.exit(0);
            }

        }
    }

    private void createDroid() {
        screen.showDroids();

        int ch, len = DroidEnum.values().length;
        while (true) {
            System.out.println(BLACK_BOLD + "Choose a droid: ");
            ch = in.nextInt();
            in.nextLine();

            if (ch == 0)
                return;

            if (ch < 0 || ch >= len)
                System.out.println(RED_BOLD_BRIGHT + "INVALID CHOICE");
            else break;
        }

        System.out.println("Set a name: ");
        String name = in.nextLine();

        switch (ch) {
            case 1 -> droids.add(new Jedi(name));
            case 2 -> droids.add(new Medic(name));
        }

        System.out.println(GREEN_BOLD_BRIGHT + "DROID HAS BEEN CREATED SUCCESSFULLY!");

    }

    private void showDroidsList() {
        if (droids.size() == 0)
            System.out.println(RED_BOLD_BRIGHT + "LIST OF DROIDS IS EMPTY. CREATE THEM!");
        screen.showDroids(droids);
        screen.drawLine();
    }

    private void runDuel() throws IOException {
        if (droids.size() < 2) {
            System.out.println(RED_BOLD_BRIGHT + "IT ISN'T ENOUGH DROIDS TO RUN A DUEL. \n\t\tRECOMMENDED SIZE OF LIST IS AT LEAST 2.");
            screen.drawLine();
            return;
        }

        screen.showDroids(droids);
        System.out.println("Choose two droids to run a duel: ");
        int ch1, ch2;
        int len = droids.size();

        while (true) {
            ch1 = in.nextInt();
            ch2 = in.nextInt();

            if ((--ch1 < 0 || ch1 >= len) || (--ch2 < 0 || ch2 >= len))
                System.out.println(RED_BOLD_BRIGHT + "INVALID CHOICE. TRY AGAIN!");
            else break;
        }

        battle = new Duel(droids.get(ch1), droids.get(ch2));
        battle.fight();

        String result = ((Duel) battle).getMsg();
        if (write && fileWriter != null)
        {
            fileWriter.write(result);
            fileWriter.close();
        } else
            System.out.print(result);

        screen.drawLine();
    }

    private void runTeamFight() {
        if (droids.size() < 4) {
            System.out.println(RED_BOLD_BRIGHT + "IT ISN'T ENOUGH DROIDS TO RUN A DUEL. \n\t\tRECOMMENDED SIZE OF LIST IS AT LEAST 4.");
            screen.drawLine();
            return;
        }

        System.out.println("Enter size of team: ");
        int len = in.nextInt();

        in.nextLine();
        String name1, name2;

        System.out.println("Set a name for the fist team: ");
        name1 = in.nextLine();

        System.out.println("Set a name for the second team: ");
        name2 = in.nextLine();

        screen.showDroids(droids);

        Team team1 = teamInit(name1, len);
        Team team2 = teamInit(name2, len);

        battle = new TeamFight(team1, team2);
        battle.fight();
    }

    private Team teamInit(String name, int size) {

        System.out.println("Choose droids for team: ");

        List<Droid> members = new ArrayList<>();

        while (size > 0) {
            int ch = in.nextInt();

            members.add(droids.get(ch - 1));

            size--;
        }

        Team team = new Team(name, members);

        return team;
    }

    private void writeBattle() {
        System.out.println("Enter a filename to write: ");
        String name = in.next();

        try {
            fileWriter = new FileWriter(String.format("src/resources/%s.txt", name));
            write = true;
        } catch (IOException e) {
            System.out.println(RED_BOLD + "File was not founded!" + BLACK_BOLD);
        }

    }

    private void readBattle() {
        System.out.println("Enter a filename to read: ");
        String name = in.next();

        try {
            Scanner reader = new Scanner(new File(String.format("src/resources/%s.txt", name)));

            while (reader.hasNext())
                System.out.println(reader.nextLine());

        } catch (FileNotFoundException e) {
            System.out.println(RED_BOLD + "File was not founded!" + BLACK_BOLD);
        }


    }
}
