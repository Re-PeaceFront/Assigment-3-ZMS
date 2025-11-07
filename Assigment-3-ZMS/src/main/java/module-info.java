/**
 * Module descriptor for the Zoo Management System application.
 * This file correctly declares all necessary modules and packages.
 */
module com.example.assigment3zms {
    // Standard JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    // Required for JavaFX properties (IntegerProperty, StringProperty, etc.)
    requires javafx.base;

    // Open main package for FXML loading
    opens com.example.assigment3zms to javafx.fxml;
    exports com.example.assigment3zms; // error disappears when emmanuelle uploads her gui

    // Open Controllers package for FXML loading
    exports com.example.assigment3zms.Controllers;
    opens com.example.assigment3zms.Controllers to javafx.fxml;

    // Open Model package for FXML and JavaFX properties
    exports com.example.assigment3zms.Model;
    opens com.example.assigment3zms.Model to javafx.fxml, javafx.base;

    // Export Helpers package (for ImportHelper)
    exports com.example.assigment3zms.Helpers;
    opens com.example.assigment3zms.Helpers to javafx.fxml;
}
