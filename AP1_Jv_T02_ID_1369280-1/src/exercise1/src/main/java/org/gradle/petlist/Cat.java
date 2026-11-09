package org.gradle.petlist;

class Cat extends Animal {
    public Cat(String petName, int petAge) {
        super(petName, petAge);
    }

    @Override
    public String toString() {
        return "Cat name = " + getPetName() + ", " + "age = " + getPetAge();
    }
}
