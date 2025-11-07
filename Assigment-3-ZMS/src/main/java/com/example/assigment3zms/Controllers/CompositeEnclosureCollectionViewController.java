package com.example.assigment3zms.Controllers;

import com.example.assigment3zms.Model.EnclosureCollection;
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
