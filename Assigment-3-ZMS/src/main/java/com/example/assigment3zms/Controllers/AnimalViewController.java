package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller for the animal-view.fxml.
 * Handles creating and editing animals.
 * 
 * @author Generated/Fixed for Assignment 3
 */
public class AnimalViewController {
    @FXML
    private ChoiceBox<String> aTypeChoiceBox;

    @FXML
    private Spinner<Integer> aAgeSpinner;

    @FXML
    private TextField aNameTextField;

    private Animal currentAnimal;
    private Enclosure currentEnclosure;
    private boolean isEditMode;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        // Initialize the choice box with available animal types
        aTypeChoiceBox.getItems().addAll("Lion", "Tiger", "Cougar");
        
        // Initialize the age spinner with proper range and default values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0, 1);
        aAgeSpinner.setValueFactory(valueFactory);
        aAgeSpinner.setEditable(true);
    }

    /**
     * Sets the animal data for editing or creating a new animal.
     * 
     * @param animal The animal to edit, or null for creating a new animal
     * @param enclosure The enclosure where the animal belongs or will be added
     */
    public void setAnimalData(Animal animal, Enclosure enclosure) {
        this.currentAnimal = animal;
        this.currentEnclosure = enclosure;
        this.isEditMode = (animal != null);

        if (isEditMode) {
            // Edit mode - populate fields with existing animal data
            if (animal != null) {
                aNameTextField.setText(animal.getName());
                aAgeSpinner.getValueFactory().setValue(animal.getAge());
                aTypeChoiceBox.setValue(animal.getSpecies());
            }
        } else {
            // Add mode - clear fields for new animal
            aNameTextField.clear();
            aAgeSpinner.getValueFactory().setValue(0);
            aTypeChoiceBox.setValue(null);
        }
    }

    /**
     * Handles the "Save" button click.
     */
    @FXML
    protected void onSaveButtonClick() {
        // Validate input
        String name = aNameTextField.getText();
        String type = aTypeChoiceBox.getValue();
        Integer age = aAgeSpinner.getValue();

        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Please enter a valid name.");
            return;
        }

        if (type == null) {
            showAlert("Invalid Input", "Please select an animal type.");
            return;
        }

        if (age == null || age < 0) {
            showAlert("Invalid Input", "Please enter a valid age (0 or greater).");
            return;
        }

        try {
            if (isEditMode) {
                // Edit existing animal
                currentAnimal.setName(name.trim());
                currentAnimal.setAge(age);
            } else {
                // Create new animal
                Animal newAnimal = createAnimalByType(type, name.trim(), age);
                if (newAnimal != null) {
                    currentEnclosure.addAnimal(newAnimal);
                }
            }

            // Close the window
            Stage stage = (Stage) aNameTextField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            showAlert("Error", "An error occurred while saving: " + e.getMessage());
        }
    }

    /**
     * Creates a new animal instance based on the selected type.
     * 
     * @param type The animal type (Lion, Tiger, Cougar)
     * @param name The animal name
     * @param age The animal age
     * @return A new Animal instance, or null if type is not recognized
     */
    private Animal createAnimalByType(String type, String name, int age) {
        switch (type) {
            case "Lion":
                return new Lion(name, age);
            case "Tiger":
                return new Tiger(name, age);
            case "Cougar":
                return new Cougar(name, age);
            default:
                showAlert("Error", "Unknown animal type: " + type);
                return null;
        }
    }

    /**
     * Handles the "Back" button click.
     */
    @FXML
    protected void onBackButtonClick() {
        Stage stage = (Stage) aNameTextField.getScene().getWindow();
        stage.close();
    }

    /**
     * Shows an alert dialog with the given title and message.
     * 
     * @param title The alert title
     * @param message The alert message
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}