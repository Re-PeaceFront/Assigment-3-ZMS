package com.example.assigment3zms.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompositeEnclosureCollection implements EnclosureCollection{
    private final String name;
    private final ObservableList<EnclosureCollection> children;

   public CompositeEnclosureCollection(String name){
       this.name = name;
       this.children = FXCollections.observableArrayList();
   }

   public void addCollection(EnclosureCollection collection) {
       this.children.add(collection);
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
       return this.name;
    }

}
