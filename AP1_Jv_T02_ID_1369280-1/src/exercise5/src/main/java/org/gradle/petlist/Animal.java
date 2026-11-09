package org.gradle.petlist;

public abstract class Animal {
    private String petName;
    private int petAge;

    public Animal(String petName, int petAge) {
        this.petName = petName;
        this.petAge = petAge;
    }

    public String getPetName() {
        return petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public abstract String toString();

    public abstract double goToWalk();
}
