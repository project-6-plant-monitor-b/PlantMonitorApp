module com.example.plantmonitorapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires at.client;


    opens com.example.plantmonitorapp to javafx.fxml;
    exports com.example.plantmonitorapp;
}