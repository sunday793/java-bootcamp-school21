package org.gradle.petlist;

import java.util.List;

public class AnimalIterator implements BaseIterator<Animal> {
    private List<Animal> listOfAnimals;
    private int index;

    public AnimalIterator(List<Animal> listOfAnimals) {
        this.listOfAnimals = listOfAnimals;
        this.index = 0;
    }

    @Override
    public Animal next() {
        Animal currentAnimal = listOfAnimals.get(index);
        index++;
        return currentAnimal;
    }

    @Override
    public boolean hasNext() {
        return index < listOfAnimals.size();
    }

    @Override
    public void reset() {
        index = 0;
    }
}
