package com.example.assigment3zms.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Emmanuelle
 * This class represents a composite node in the Composite Design Pattern
 * It groups multiple {@link EnclosureCollection} objects together.
 */

public class CompositeEnclosureCollection implements EnclosureCollection{

    /**
     * The display name of the composite collection.
     */
    private final String name;

    /**
     * The list of child enclosures contained in the node.
     */
    private final ObservableList<EnclosureCollection> children;

    /**
     * This method creates a new composite collection with the given name.
     * @param name is the name of the composite collection.
     */
   public CompositeEnclosureCollection(String name){
       this.name = name;
       this.children = FXCollections.observableArrayList();
   }

    /**
     * This method adds a child enclosure or collection to the composite
     * @param collection is the child element to add.
     */
   public void addCollection(EnclosureCollection collection) {
       this.children.add(collection);
   }

    /**
     * Returns the name of the collection
     * @return the display name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of children contained in the composite
     * @return an {@link ObservableList} of child collections
     */
    @Override
    public ObservableList<EnclosureCollection> getChildren() {
        return this.children;
    }

    /**
     * Indicated that the object is a composite node.
     * @return true, because the class represents a composite
     */
    @Override
    public boolean isComposite() {
        return true;
    }

    /**
     * Returns the name of the collection for display in the ListView
     * @return the name of the collection
     */
    @Override
    public String toString() {
       return this.name;
    }

}
