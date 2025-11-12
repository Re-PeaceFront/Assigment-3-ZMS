package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Helpers.ImportHelper;
import com.example.assigment3zms.Model.CompositeEnclosureCollection;
import com.example.assigment3zms.Model.Enclosure;
import com.example.assigment3zms.Model.EnclosureCollection;
import com.example.assigment3zms.ZooApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Emmanuelle
 * Controller for the CompositeEnclosureCollection view
 * It displays a list of enclosures and collections by opening an enclosure view
 */
public class CompositeEnclosureCollectionViewController {
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

    /**
     * This method initializes the view by loading the Big Cats composite collection,
     * filling the ListView, and enabling live search filtering.
     */
    @FXML
    private void initialize() {
        // Load Big Cats from ImportHelper

        rootCollection = ImportHelper.createAnimals();

        // Load the children into the ListView
        displayedChildren = FXCollections.observableArrayList(rootCollection.getChildren());
        enclosureListView.setItems(displayedChildren);

    }

    /**
     * Sets the root collection (composite) to be displayed in the view
     * @param collection to be displayed(Tiger Cubs. Tiger habitats)
     */

    private void setRootCollection(CompositeEnclosureCollection collection) {
        this.rootCollection = collection;
        displayedChildren = FXCollections.observableArrayList(rootCollection.getChildren());
        enclosureListView.setItems(displayedChildren);
    }

    /**
     * Handles the Open button
     * If the selected item is a composite, it opens another compositeenclosurecollectionview
     * If it is an enclosure, the enclosure view window is opened.
     */
    @FXML
    private void onOpenButtonClick() {
        EnclosureCollection selected =  enclosureListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            if (selected.isComposite()) {
                FXMLLoader loader = new FXMLLoader(ZooApplication.class.getResource("compositeenclosurecollection-view.fxml"));
                Parent view = loader.load();

                CompositeEnclosureCollectionViewController controller = loader.getController();
                controller.setRootCollection((CompositeEnclosureCollection) selected);

                Stage nextStage = new Stage();
                nextStage.setScene(new Scene(view));
                nextStage.initModality(Modality.WINDOW_MODAL);
                nextStage.initOwner(openButton.getScene().getWindow());
                nextStage.showAndWait();
            }
            else
            {
            FXMLLoader loader = new FXMLLoader(ZooApplication.class.getResource("enclosure-view.fxml"));
            Parent view = loader.load();

            EnclosureViewController controller = loader.getController();
            controller.setEnclosure((Enclosure) selected);

            Stage nextStage = new Stage();
            nextStage.setScene(new Scene(view));
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(openButton.getScene().getWindow());
            nextStage.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Closes the current window when the Back button is pressed.
     */

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the current window when the Close button is pressed.
     */
    @FXML
    private void onCloseButtonClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
