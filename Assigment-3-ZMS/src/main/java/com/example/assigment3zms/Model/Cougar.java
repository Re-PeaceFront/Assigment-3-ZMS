package com.example.assigment3zms.Model;
/**
    * Represents a Cougar animal in the zoo.
 * @author Rene
 * @version 2.0
 */
public class Cougar extends Animal {
    public Cougar(String name, int age) {
        super(name, age);
    }

    @Override
    public String getSpecies() {
        return "Cougar";
    }
}
