package org.gradle.petlist;

public class Hamster extends Animal implements Herbivore {
    public Hamster(String petName, int petAge) { super(petName, petAge); }

    @Override
    public String chill() {
        return "I can chill for 8 hours";
    }

    @Override
    public String toString() {
        return "Hamster name = %s, age = %d. ".formatted(getPetName(), getPetAge()) + chill();
    }
}
