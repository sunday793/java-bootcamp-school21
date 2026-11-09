package org.gradle.petlist;

public class Dog extends Animal {
    public Dog(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String toString() {
        return "Dog name = %s, age = %d".formatted(getPetName(), getPetAge());
    }

}
