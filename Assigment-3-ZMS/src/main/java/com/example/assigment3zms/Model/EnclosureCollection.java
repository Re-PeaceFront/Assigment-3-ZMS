package com.example.assigment3zms.Model;

import javafx.collections.ObservableList;

/**
 * Component interface for the Composite Design Pattern.
 * Defines the common operations for both leaf (Enclosure) and
 * composite (CompositeEnclosureCollection) nodes.
 *
 * @author Ren (Student)
 * @version 1.0
 */
public interface EnclosureCollection {

    /**
     * Gets the name of the enclosure or collection.
     *
     * @return The display name.
     */
    String getName();

    /**
     * Gets the children of this node.
     * For a Composite, this returns its list of sub-collections.
     * For a Leaf (Enclosure), this returns null.
     *
     * @return An ObservableList of EnclosureCollection children, or null.
     */
    ObservableList<EnclosureCollection> getChildren();

    /**
     * Checks if this node is a composite node (i.e., can have children).
     *
     * @return true if it's a composite (like a Section),
     * false if it's a leaf (like an Enclosure).
     */
    boolean isComposite();
}
