package org.gradle.petlist;

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getPetName() + ", " + "age = " + getPetAge();
    }
}
