package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Model.Animal;
import com.example.assigment3zms.Model.Enclosure;
import com.example.assigment3zms.ZooApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the enclosure-view.fxml.
 * Handles adding, editing, and removing animals (CRUD).
 */
public class EnclosureViewController {

    @FXML
    private Label enclosureNameLabel;
    @FXML
    private ListView<Animal> animalListView;
    @FXML
    private Button editAnimalButton;
    @FXML
    private Button removeAnimalButton;

    private Enclosure currentEnclosure;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        animalListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean animalSelected = (newVal != null);
            editAnimalButton.setDisable(!animalSelected);
            removeAnimalButton.setDisable(!animalSelected);
        });

        editAnimalButton.setDisable(true);
        removeAnimalButton.setDisable(true);
    }

    /**
     * Sets the model (Enclosure) for this view.
     */
    public void setEnclosure(Enclosure enclosure) {
        this.currentEnclosure = enclosure;
        enclosureNameLabel.setText(enclosure.getName());
        animalListView.setItems(enclosure.getAnimals());
    }

    /**
     * Handles the "Add Animal" button click.
     */
    @FXML
    protected void onAddAnimalClick(ActionEvent pEvent) {
        openAnimalView(null, pEvent, "Add New Animal");
    }

    /**
     * Handles the "Edit Selected" button click.
     */
    @FXML
    protected void onEditAnimalClick(ActionEvent pEvent) {
        Animal selectedAnimal = animalListView.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            openAnimalView(selectedAnimal, pEvent, "Edit " + selectedAnimal.getName());
        }
    }

    /**
     * Handles the "Remove Selected" button click.
     */
    @FXML
    protected void onRemoveAnimalClick(ActionEvent pEvent) {
        Animal selectedAnimal = animalListView.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            currentEnclosure.removeAnimal(selectedAnimal);
        }
    }

    /**
     * A helper method to open the animal-view.fxml modal window.
     */
    private void openAnimalView(Animal animal, ActionEvent pEvent, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("animal-view.fxml"));
            Parent view = fxmlLoader.load();

            AnimalViewController animalViewController = fxmlLoader.getController();
            animalViewController.setAnimalData(animal, currentEnclosure);

            Scene nextScene = new Scene(view, 400, 300);
            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.setTitle(title);
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
            nextStage.showAndWait();
            
            animalListView.refresh();

        } catch (IOException e) {
            System.err.println("Failed to load animal-view.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the "Back" button click.
     */
    @FXML
    protected void onBackClick(ActionEvent pEvent) {
        Stage stage = (Stage) enclosureNameLabel.getScene().getWindow();
        stage.close();
    }
}
