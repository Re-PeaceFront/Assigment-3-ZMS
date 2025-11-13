package com.example.assigment3zms.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Composite node in the Composite pattern.
 * This class can contain multiple EnclosureCollections (both Enclosures and other Composites).
 * 
 * @author Generated for Assignment 3
 */
public class CompositeEnclosureCollection implements EnclosureCollection {
    private final String name;
    private final ObservableList<EnclosureCollection> children;

    /**
     * Constructs a new CompositeEnclosureCollection with a given name.
     */
    public CompositeEnclosureCollection(String name) {
        this.name = name;
        this.children = FXCollections.observableArrayList();
    }

    /**
     * Adds an EnclosureCollection to this composite.
     */
    public void addCollection(EnclosureCollection collection) {
        this.children.add(collection);
    }

    /**
     * Removes an EnclosureCollection from this composite.
     */
    public void removeCollection(EnclosureCollection collection) {
        this.children.remove(collection);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ObservableList<EnclosureCollection> getChildren() {
        return this.children;
    }

    @Override
    public boolean isComposite() {
        return true;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
