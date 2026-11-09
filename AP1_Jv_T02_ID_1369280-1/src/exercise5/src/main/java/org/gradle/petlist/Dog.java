package org.gradle.petlist;

import java.util.concurrent.TimeUnit;

public class Dog extends Animal {
    public Dog(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String toString() {
        return "Dog name = %s, age = %d".formatted(getPetName(), getPetAge());
    }

    @Override
    public double goToWalk() {
        double walkTime = getPetAge() * 0.5;
        try {
            long milliseconds = (long) (walkTime * 1000);
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return walkTime;
    }

}
