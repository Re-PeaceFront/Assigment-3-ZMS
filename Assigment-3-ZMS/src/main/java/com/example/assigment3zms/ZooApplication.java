package com.example.assigment3zms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Zoo Management System.
 * This class loads the primary view and starts the JavaFX application.
 */
public class ZooApplication extends Application {

    /**
     * The main entry point for all JavaFX applications.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("composite-enclosure-collection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Zoo Management System - Big Cats");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
