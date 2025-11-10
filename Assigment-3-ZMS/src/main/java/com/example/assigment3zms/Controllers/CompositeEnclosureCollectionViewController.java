package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Helpers.ImportHelper;
import com.example.assigment3zms.Model.CompositeEnclosureCollection;
import com.example.assigment3zms.Model.Enclosure;
import com.example.assigment3zms.Model.EnclosureCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompositeEnclosureCollectionViewController {
    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<EnclosureCollection> enclosureListView;

    @FXML
    private Button openButton;

    @FXML
    private Button backButton;

    @FXML
    private Button closeButton;

    private CompositeEnclosureCollection rootCollection;
    private ObservableList<EnclosureCollection> displayedChildren;

    @FXML
    private void initialize() {
        // Load Big Cats from ImportHelper
        rootCollection = ImportHelper.createAnimals();

        // Load the children into the ListView
        displayedChildren = FXCollections.observableArrayList(rootCollection.getChildren());
        enclosureListView.setItems(displayedChildren);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filterList(newValue));

    }

    private void filterList(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            displayedChildren.setAll(rootCollection.getChildren());
            return;
        }

        displayedChildren.setAll(rootCollection.getChildren().stream()
                .filter(collection -> collection.getName().toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                            .toList()
        );
    }

    @FXML
    private void onOpenButtonClick() {
        EnclosureCollection selected =  enclosureListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (selected.isComposite()) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Selection");
            alert.setHeaderText("Invalid Selection");
            alert.setContentText("Please select an enclosure");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("enclosure-view.fxml"));
            Parent view = loader.load();

            EnclosureViewController controller = loader.getController();
            controller.setEnclosure((Enclosure) selected);

            Stage nextStage = new Stage();
            nextStage.setScene(new Scene(view));
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(openButton.getScene().getWindow());
            nextStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCloseButtonClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
