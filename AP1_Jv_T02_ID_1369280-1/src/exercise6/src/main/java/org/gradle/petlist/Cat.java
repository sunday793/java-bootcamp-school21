package org.gradle.petlist;

public class Cat extends Animal {
    public Cat(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String toString() {
        return "Cat name = %s, age = %d".formatted(getPetName(), getPetAge());
    }

}
