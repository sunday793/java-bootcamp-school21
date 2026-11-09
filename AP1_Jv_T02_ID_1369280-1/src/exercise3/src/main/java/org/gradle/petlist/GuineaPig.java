package org.gradle.petlist;

public class GuineaPig extends Animal implements Herbivore {
    public GuineaPig(String petName, int petAge) { super(petName, petAge); }

    @Override
    public String chill() {
        return "I can chill for 12 hours";
    }

    @Override
    public String toString() {
        return "GuineaPig name = %s, age = %d. ".formatted(getPetName(), getPetAge()) + chill();
    }
}
