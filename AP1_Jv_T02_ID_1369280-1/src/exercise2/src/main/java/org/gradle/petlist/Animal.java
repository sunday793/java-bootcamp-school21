package org.gradle.petlist;

public abstract class Animal {
    private String petName;
    private int petAge;
    private Double petWeight;

    public Animal(String petName, int petAge, Double petWeight) {
        this.petName = petName;
        this.petAge = petAge;
        this.petWeight = petWeight;
    }

    public String getPetName() {
        return petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public Double getPetWeight() { return petWeight;}

    public abstract String toString();
    public abstract Double getFeedInfoKg();

}
