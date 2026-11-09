package org.gradle.petlist;

public class Dog extends Animal implements Omnivore {
    public Dog(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String hunt() {
        return "I can hunt for robbers";
    }

    @Override
    public String toString() {
        return "Dog name = %s, age = %d. ".formatted(getPetName(), getPetAge()) + hunt();
    }

}
