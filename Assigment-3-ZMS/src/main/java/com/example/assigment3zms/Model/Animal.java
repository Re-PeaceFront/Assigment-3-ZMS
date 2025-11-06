package com.example.assigment3zms.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

/**
 * Abstract base class for all animals in the zoo.
 * Provides basic properties: name and age.
 *
 * REFACTOR: Changed fields to JavaFX Properties (StringProperty, IntegerProperty)
 * to support data binding for the MVC pattern. This is a much stronger
 * implementation of MVC.
 *
 * FIX: Changed age from 'double' (in repo) to 'int' to match the
 * provided ImportHelper.java class.
 *
 * @author Ren (Student)
 * @version 2.0
 */
public abstract class Animal {
    private final StringProperty name;
    private final IntegerProperty age;

    /**
     * Constructor for the Animal class.
     *
     * @param name The name of the animal.
     * @param age  The age of the animal (as an int).
     */
    public Animal(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be smaller than 0!");
        }
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }

    // --- JavaFX Property Getters ---

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    // --- Standard Getters ---

    public String getName() {
        return name.get();
    }

    public int getAge() {
        return age.get();
    }

    // --- Standard Setters ---

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name.set(name);
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be smaller than 0!");
        }
        this.age.set(age);
    }

    /**
     * Returns the species of the animal.
     *
     * @return A string representing the animal's species (e.g., "Lion").
     */
    public abstract String getSpecies();

    /**
     * Provides a string representation of the animal for display in ListViews.
     *
     * @return A string formatted as "Name (Age years) - Species".
     */
    @Override
    public String toString() {
        return String.format("%s (%d years) - %s", getName(), getAge(), getSpecies());
    }
}