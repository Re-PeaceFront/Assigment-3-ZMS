package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the animal-view.fxml.
 * Provides a form to add a new animal or edit an existing one (CRUD).
 * This controller is adapted to match the FXML file.
 */
public class AnimalViewController {

    @FXML
    private TextField aNameTextField;

    @FXML
    private Spinner<Integer> aAgeSpinner;

    @FXML
    private ChoiceBox<String> aTypeChoiceBox;

    // Model data passed from the previous controller
    private Animal currentAnimal;
    private Enclosure currentEnclosure;
    private boolean isEditMode = false;

    /**
     * Initializes the controller. This is called when the FXML is loaded.
     */
    @FXML
    public void initialize() {
        // Set up the choices for the ChoiceBox
        aTypeChoiceBox.setItems(FXCollections.observableArrayList(
                "Lion",
                "Tiger",
                "Cougar"
        ));

        // Configure the Spinner to be for integer ages
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // (min, max, initial)
        aAgeSpinner.setValueFactory(valueFactory);
    }

    /**
     * Called by EnclosureViewController to pass in the required data.
     *
     * @param animal The animal to edit (if in edit mode), or null (if in add mode).
     * @param enclosure The enclosure to which the animal belongs.
     */
    public void setAnimalData(Animal animal, Enclosure enclosure) {
        this.currentEnclosure = enclosure;
        this.currentAnimal = animal;

        if (animal != null) {
            // --- EDIT MODE ---
            this.isEditMode = true;
            
            // Fill the form with the animal's data
            aNameTextField.setText(animal.getName());
            aAgeSpinner.getValueFactory().setValue(animal.getAge());
            aTypeChoiceBox.setValue(animal.getSpecies());
            
            // Don't let user change species of an existing animal
            aTypeChoiceBox.setDisable(true);
        } else {
            // --- ADD MODE ---
            this.isEditMode = false;
            // Allow user to select a species
            aTypeChoiceBox.setDisable(false);
        }
    }

    /**
     * Handles the "Save" button click.
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent pEvent) {
        // 1. Get and validate data from the form
        String name = aNameTextField.getText();
        String species = aTypeChoiceBox.getValue();
        int age;

        // Basic validation
        if (name == null || name.trim().isEmpty() || species == null || species.isEmpty()) {
            System.err.println("All fields are required.");
            // error handling here
            return;
        }
        
        try {
            age = aAgeSpinner.getValue();
            if (age < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("Age must be a positive number.");
            // error handling here
            return;
        }

        // 2. Update the Model
        if (isEditMode) {
            // Update existing animal
            currentAnimal.setName(name);
            currentAnimal.setAge(age);
        } else {
            // Create a new animal based on the selected species
            Animal newAnimal = null;
            switch (species) {
                case "Lion":
                    newAnimal = new Lion(name, age);
                    break;
                case "Tiger":
                    newAnimal = new Tiger(name, age);
                    break;
                case "Cougar":
                    newAnimal = new Cougar(name, age);
                    break;
                default:
                    System.err.println("Unknown species selected.");
                    return; // Don't proceed
            }
            // Add the new animal to the enclosure
            currentEnclosure.addAnimal(newAnimal);
        }

        // 3. Close this window
        closeWindow();
    }

    /**
     * Handles the "Back" button click.
     */
    @FXML
    protected void onBackButtonClick(ActionEvent pEvent) {
        closeWindow();
    }

    /**
     * Helper method to get the current window and close it.
     */
    private void closeWindow() {
        // Get the stage (window) from any component in the scene
        Stage stage = (Stage) aNameTextField.getScene().getWindow();
        stage.close();
    }
}