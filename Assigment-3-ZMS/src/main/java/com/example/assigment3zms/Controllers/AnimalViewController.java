package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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

    private Animal currentAnimal;
    private Enclosure currentEnclosure;
    private boolean isEditMode = false;

    /**
     * Initializes the controller. This is called when the FXML is loaded.
     */
    @FXML
    public void initialize() {
        aTypeChoiceBox.setItems(FXCollections.observableArrayList(
                "Lion",
                "Tiger",
                "Cougar"
        ));

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
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
            this.isEditMode = true;
            
            aNameTextField.setText(animal.getName());
            aAgeSpinner.getValueFactory().setValue(animal.getAge());
            aTypeChoiceBox.setValue(animal.getSpecies());
            
            aTypeChoiceBox.setDisable(true);
        } else {
            this.isEditMode = false;
            aTypeChoiceBox.setDisable(false);
        }
    }

    /**
     * Handles the "Save" button click.
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent pEvent) {
        String name = aNameTextField.getText();
        String species = aTypeChoiceBox.getValue();
        int age;

        if (name == null || name.trim().isEmpty() || species == null || species.isEmpty()) {
            System.err.println("All fields are required.");
            return;
        }
        
        try {
            age = aAgeSpinner.getValue();
            if (age < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("Age must be a positive number.");
            return;
        }

        if (isEditMode) {
            currentAnimal.setName(name);
            currentAnimal.setAge(age);
        } else {
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
                    return;
            }
            currentEnclosure.addAnimal(newAnimal);
        }

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
        Stage stage = (Stage) aNameTextField.getScene().getWindow();
        stage.close();
    }
}