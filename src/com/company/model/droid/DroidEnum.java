package com.company.model.droid;

public enum DroidEnum {

    JEDI ("JEDI"),
    MEDIC ("MEDIC"),
    PILOT ("PILOT"),
    ROGER ("ROGER"),
    VIPER ("VIPER");

    private final String name;

    DroidEnum(String name) {
        this.name = name;
    }

}
