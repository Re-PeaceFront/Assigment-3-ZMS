package com.example.assigment3zms.Model;

public class Cougar extends Animal {
    public Cougar(String name, int age) {
        super(name, age);
    }

    @Override
    public String getSpecies() {
        return "Cougar";
    }
}
