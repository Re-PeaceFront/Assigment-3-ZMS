module com.example.assigment3zms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assigment3zms to javafx.fxml;
    exports com.example.assigment3zms;
    exports com.example.assigment3zms.Controllers;
    opens com.example.assigment3zms.Controllers to javafx.fxml;
    exports com.example.assigment3zms.Model;
    opens com.example.assigment3zms.Model to javafx.fxml;
}