package org.gradle.petlist;

public class Dog extends Animal {
    public Dog(String petName, int petAge, Double petWeight) {
        super(petName, petAge, petWeight);
    }

    @Override
    public Double getFeedInfoKg() {
        return getPetWeight() * 0.3;
    }

    @Override
    public String toString() {
        return "Dog name = %s, age = %d, mass = %.2f, feed = %.2f".formatted(getPetName(), getPetAge(), getPetWeight(), getFeedInfoKg());
    }

}
