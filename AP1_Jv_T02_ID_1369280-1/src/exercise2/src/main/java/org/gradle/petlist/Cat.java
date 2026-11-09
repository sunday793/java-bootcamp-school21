package org.gradle.petlist;

public class Cat extends Animal {
    public Cat(String petName, int petAge, Double petWeight) {
        super(petName, petAge, petWeight);
    }

    @Override
    public Double getFeedInfoKg() {
        return getPetWeight() * 0.1;
    }

    @Override
    public String toString() {
        return "Cat name = %s, age = %d, mass = %.2f, feed = %.2f".formatted(getPetName(), getPetAge(), getPetWeight(), getFeedInfoKg());
    }
}
