package com.example.assigment3zms.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Leaf node in the Composite pattern (Enclosure).
 * This class holds a collection of animals.
 */
public class Enclosure implements EnclosureCollection {
    private final String name;
    private final ObservableList<Animal> animals;

    /**
     * Constructs a new Enclosure with a given name.
     */
    public Enclosure(String name) {
        this.name = name;
        this.animals = FXCollections.observableArrayList();
    }

    /**
     * Adds an animal to this enclosure.
     */
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    /**
     * Removes an animal from this enclosure.
     */
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    /**
     * Gets the list of animals in this enclosure.
     */
    public ObservableList<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ObservableList<EnclosureCollection> getChildren() {
        return null; // Leaf nodes don't have composite children
    }

    @Override
    public boolean isComposite() {
        return false;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
