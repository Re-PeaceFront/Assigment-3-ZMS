package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Helpers.ImportHelper;
import com.example.assigment3zms.Model.CompositeEnclosureCollection;
import com.example.assigment3zms.Model.EnclosureCollection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    private CompositeEnclosureCollection aCompositeEnclosureCollection;

    @FXML
    public void initialize() {
        setComposite(ImportHelper.createAnimals());
        // TODO once composite implements EnclosureCollection interface, uncomment the next line
        // enclosureListView.setItems(FXCollections.observableArrayList(this.aCompositeEnclosureCollection.getChildren()));
    }

    public void setComposite(CompositeEnclosureCollection pCompositeEnclosureCollection) {
        this.aCompositeEnclosureCollection = pCompositeEnclosureCollection;
    }

    @FXML
    private void onOpenButtonClick() {

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
