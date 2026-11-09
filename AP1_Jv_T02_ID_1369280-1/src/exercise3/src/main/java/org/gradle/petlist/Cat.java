package org.gradle.petlist;

public class Cat extends Animal implements Omnivore {
    public Cat(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String hunt() {
        return "I can hunt for mice";
    }

    @Override
    public String toString() {
        return "Cat name = %s, age = %d. ".formatted(getPetName(), getPetAge()) + hunt();
    }
}
