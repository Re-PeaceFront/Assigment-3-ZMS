package com.example.assigment3zms.Model;
/**
 * Represents a Lion, inheriting from the Animal base class.
 */
public class Lion extends Animal {
    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public String getSpecies() {
        return "Lion";
    }
}